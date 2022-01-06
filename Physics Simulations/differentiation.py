# -*- coding: utf-8 -*-
"""Differentiation.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/github/WereszczynskiClasses/assignment-week-6-andrguardia/blob/main/Central-Difference.ipynb

# Central difference

A simple improvement to the forward and backward differences is the *central difference* approach.  Central difference is similar to the forward and backward approaches, except now we use both one point before and another after the one we are interested in computing a derivative for.  To see why this works, consider the two Taylor series:

$f\left(x+h\right) = f\left(x\right)  + h \cdot f'\left(x\right) + \frac{h^2}{2} \cdot f''\left(x\right) + \frac{h^3}{3!} \cdot f'''\left(x\right)+...$

$f\left(x-h\right) = f\left(x\right)  - h \cdot f'\left(x\right) + \frac{h^2}{2} \cdot f''\left(x\right) - \frac{h^3}{3!} \cdot f'''\left(x\right)+...$

Subtracting the second equation from the first and rearranging for $f'\left(x\right)$ gives:

$f'\left(x\right) = \frac{f\left(x+h\right)-f\left(x-h\right)}{2h} - \frac{1}{3} h^2 f'''\left(x\right)+...$

This gives us an estimate for the derivative of:

$f'\left(x\right) = \frac{f\left(x+h\right)-f\left(x-h\right)}{2h}$

Which has a truncation error that is now on the order of $h^2$, not $h$.  This can lead to a significant improvement in accuracy!

## Assignment

Write a python program that implements the central, forward, and backward difference approaches.  Use this program to calculate the numerical derivative of the function 

$f\left(x\right) = \sin\left(x\right)$

at the point $x = 0.5$.

The program should have a function that compares the true derivative to the numerical one, and computes the percentage error to quantify this difference between the true and computed values:

> $error = \left.|\frac{\hat{y}_i-y_i}{y_i} \right.| \cdot 100$

where $y_i$ is the true value at a point and $\hat{y}_i$ is the estimate you are computing through numerical differentiation.  

Use this program  to calculate a range of different $h$ values, using 

$h = 2^{z}$

with z ranging from -50 to 1. For each value of $h$, compute the percent error.  Make a log/log plot of $h$ vs the percent error for all three estimates.  What value of $h$ minimizes the error for each estimate?  Which method is best to use?
"""

import numpy as np
import matplotlib.pyplot as plt

def derivative(f,a,h,method='central'):
#f : Function of of one variable
#a : Point where derivative must be calculated
#method : String that indicates what method to use (central, forward, backward)
#h : Step size in difference formula
#Difference formula:
#central: f(a+h) - f(a-h))/2h
#forward: f(a+h) - f(a))/h
 #backward: f(a) - f(a-h))/h            
  
    if method == 'central':
        return (f(a + h) - f(a - h))/(2*h)
    elif method == 'forward':
        return (f(a + h) - f(a))/h
    elif method == 'backward':
        return (f(a) - f(a - h))/h
    else:
        raise ValueError("Method must be 'central', 'forward' or 'backward'.")

derivative(np.sin, 0.5, 0.01,method='forward')

derivative(np.sin, 0.5, 0.01,method='backward')

derivative(np.sin, 0.5, 0.01,method='central')

trueVal = np.cos(0.5)
print(trueVal)

def errorfunc(ytrue,yestimate):
  #ytrue is the true derivative value
  #yestimate is the estimated derivative value
  #This function returns the error percentage of the estimation
  er = abs(yestimate-ytrue)/ytrue
  return er

z = np.arange(-50,2,0.01)
h = 2.0**z
error_forward = []
error_backward = []
error_central = []

for k in np.arange(0, len(z)):
  y_forward = derivative(np.sin, 0.5, h[k],method='forward')
  y_backward = derivative(np.sin, 0.5, h[k],method='backward')
  y_central = derivative(np.sin, 0.5, h[k],method='central')
  error_forward.append(errorfunc(trueVal,y_forward))
  error_backward.append(errorfunc(trueVal,y_backward))
  error_central.append(errorfunc(trueVal,y_central))

plt.plot(h,error_forward,'b')
plt.plot(h,error_backward,'r')
plt.plot(h,error_central,'g')
plt.ylabel('Error Percentage')
plt.xlabel('h values')
plt.title('h vs Percent Error')
plt.show()