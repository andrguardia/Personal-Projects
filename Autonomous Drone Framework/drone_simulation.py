print("------------------Start simulator (SITL)-----------------")
import dronekit_sitl import SITL
import dronekit
import time


sitl = SITL()
sitl.download('copter', '3.3', verbose=True)
sitl_args = ['-I0', '--model', 'quad', '--home=51.945102,-2.074558,0,180']
sitl.launch(sitl_args, await_ready=True, restart=True)

connection_string = 'tcp:127.0.0.1:5760'
print("Connecting to vehicle on: %s" % connection_string)
vehicle = connect(connection_string, wait_ready=True, baud=57600)

# Import DroneKit-Python
from dronekit import connect, VehicleMode

# Connect to the Vehicle.
print("Connecting to vehicle on: %s" % (connection_string,))
vehicle = connect(connection_string, wait_ready=True)
# Get some vehicle attributes (state)
print("Get some vehicle attribute values:")
print(" Global Location: %s" % vehicle.location.global_frame)
print(" GPS: %s" % vehicle.gps_0)
print(" Battery: %s" % vehicle.battery)
print(" Last Heartbeat: %s" % vehicle.last_heartbeat)
print(" Is Armable?: %s" % vehicle.is_armable)
print(" System status: %s" % vehicle.system_status.state)
print(" Mode: %s" % vehicle.mode.name)

#Setting Home location
# Get Vehicle Home location - will be `None` until first set by autopilot
while not vehicle.home_location:
    cmds = vehicle.commands
    cmds.download()
    cmds.wait_ready()
    if not vehicle.home_location:
        print (" Waiting for home location ...")
        time.sleep(1)
charger_1_location = dronekit.LocationGlobal(41.848460,-87.631830,0) #Charger One located on the Man on the Bench Park
vehicle.home_location = charger_1_location #Setting home to charger one location
# Home Location set to Charger One
print ("\n Home location: %s" % vehicle.home_location)
print(" Global Location: %s" % vehicle.location.global_frame)
#Takeoff

def arm_and_takeoff(aTargetAltitude):
    """
    Arms vehicle and fly to aTargetAltitude.
    """

    print ("Basic pre-arm checks")
    # Don't try to arm until autopilot is ready
    while not vehicle.is_armable:
        print (" Waiting for vehicle to initialise...")
        time.sleep(1)

    print ("Arming motors")
    # Copter should arm in GUIDED mode
    vehicle.mode    = VehicleMode("GUIDED")
    vehicle.armed   = True

    # Confirm vehicle armed before attempting to take off
    while not vehicle.armed:
        print (" Waiting for arming...")
        time.sleep(1)
    print( "Armed: %s" % vehicle.armed)
    print ("Taking off!")
    vehicle.simple_takeoff(aTargetAltitude) # Take off to target altitude

    # Wait until the vehicle reaches a safe height before processing the goto (otherwise the command
    #  after Vehicle.simple_takeoff will execute immediately).
    while True:
        print (" Altitude: ", vehicle.location.global_relative_frame.alt)
        #Break and return from function just below target altitude.
        if vehicle.location.global_relative_frame.alt>=aTargetAltitude*0.95:
            print ("Reached target altitude")
            break
        time.sleep(1)

arm_and_takeoff(100)
