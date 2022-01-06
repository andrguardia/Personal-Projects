# -*- coding: utf-8 -*-
"""Blackbody.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/github/WereszczynskiClasses/assignment-week-7-andrguardia/blob/main/Blackbody.ipynb

# Plank Theory
The Planck theory of thermal radiation tells us that in the
(angular) frequency interval $\omega$ to $\omega+d\omega$, a black body
of unit area radiates electromagnetically an amount of thermal energy per

$
  I(\omega) = {\hbar\over4\pi^2c^2}\,\frac{\omega^3}{(e^{\hbar\omega/k_BT}-1)}.
$

Here $\hbar$ is Planck's constant over $2\pi$, $c$ is the speed of light,
and $k_B$ is Boltzmann's constant.

Integrating this gives the total energy per unit area radiated by a black body as:

$W = {k_B^4 T^4\over4\pi^2c^2\hbar^3} \int_0^\infty \frac{x^3}{e^x-1}\>d x$

##A.

Write a program to evaluate the integral in this expression.  Explain
  what method you used, and how accurate you think your answer is. Feel free to reuse previous code that YOU wrote (as long as you wrote it!).
"""

import numpy as np
import matplotlib.pyplot as plt
#Defining the constants required to perform the  calculation
hbar = (6.62607004e-34)/(2*np.pi) #Reduced Plank Constant, units in m^2kg/s
c = 3e8 #Speed of Light,units in m/s
kb = 1.38064852e-23 #Boltzmann's Constant, units in m^2*kg/s^2*K
C = (kb**4)/(4*(np.pi**2)*(c**2)*hbar**3)

#Using the Trapezoidal Adaptive Integration code written for the previous assignment, the value of  the  integral can be calculated to  a very high degree of acurracy (around 8 figures)
def f(x):
  return (x**3)/(np.exp(x)-1)
a = 0.000001
b = 700
error = 1.0e-8
N_1 = 10
I = np.array([0.0]) 
N = [N_1/2] 
err = [10*error] 
i = 0 
while err[i]>error:  
  i += 1  
  N.append(int(N[i-1]*2)) 
  h=(b-a) / N[i]  
  x = np.linspace(a,b,N[i]+1,endpoint=True) 
  y = f(x) 
  integral = h*0.5*(y[0]+y[-1]) 
  for j in range(1,N[i]):
    integral += h * y[j]
  I=np.append(I,integral) 
  err.append(1/3*np.abs(I[i]-I[i-1])) 

Integral_Value = I[i] #We define the last calculated value of the integral as a constant

#We now define the function for the  total energy per unit area radiated by a black body

def W(T):
  return C*Integral_Value*T**4

"""##B.

Use the program you wrote in part A to plot the total energy per unit area as a function of $T$ from $0 K\leq T \leq 5000 K$.
"""

T = np.linspace(0.0,5000.0,5001) # Setting up temperature range
p = W(T) # Calculating y values for the plot

plt.plot(T,p,'r-')
plt.ylabel('Power Density [W/m^2]')
plt.xlabel('Temperature Values [K]')
plt.title('Power Density vs Temperature')
plt.show()

"""##C. 
Even before Planck gave his theory of thermal radiation around the
  turn of the 20th century, it was known that the total energy $W$ given
  off by a black body per unit area per second followed Stefan's
  law: $W = \sigma T^4$, where $\sigma$ is the Stefan--Boltzmann constant.
  Use your value for the integral above to compute a value for the
  Stefan--Boltzmann constant (in SI units) to three significant figures.
  Check your result against the known value, which you can find in books or
  on-line.  You should get good agreement.
"""

#Defining the constants required to perform the  calculation
hbar = (6.62607004e-34)/(2*np.pi) #Reduced Plank Constant, units in m^2kg/s
c = 3e8 #Speed of Light,units in m/s
kb = 1.38064852e-23 #Boltzmann's Constant, units in m^2*kg/s^2*K
C = (kb**4)/(4*(np.pi**2)*(c**2)*hbar**3)

#Using the Trapezoidal Adaptive Integration code written for the previous assignment, the value of  the  integral can be calculated to  a very high degree of acurracy (around 8 figures)
def f(x):
  return (x**3)/(np.exp(x)-1)
a = 0.000001
b = 700
error = 1.0e-3
N_1 = 10
I = np.array([0.0]) 
N = [N_1/2] 
err = [10*error] 
i = 0 
while err[i]>error:  
  i += 1  
  N.append(int(N[i-1]*2)) 
  h=(b-a) / N[i]  
  x = np.linspace(a,b,N[i]+1,endpoint=True) 
  y = f(x) 
  integral = h*0.5*(y[0]+y[-1]) 
  for j in range(1,N[i]):
    integral += h * y[j]
  I=np.append(I,integral) 
  err.append(1/3*np.abs(I[i]-I[i-1])) 

Integral_Value = I[i] #We define the last calculated value of the integral as a constant

#We now define the function for the  total energy per unit area radiated by a black body

sigma = W(4000)/4000 #Stephan-Boltzmann constant
print("The estimated value of the Stephan-Boltzmann constant",sigma,'W/m^2*K^4')

"""As can be seen above, the estimated value of the Stephan-Boltzmann constant is of the order of 1e-8, which is in accordance to the order of magnitude of the real  Stephan-Boltzmann  constant,  5.670 374 419 x 10-8 W/m^2*K^4. 

Since obtaining  agreement to this scale is very cumbersome, itcanbe concluded that the  trapezoidal estimation is a good one for estimating the value of this constant.
"""