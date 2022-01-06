from __future__ import print_function
import boto3
import dronekit
import dronekit_sitl
import time
from dronekit import connect, VehicleMode, LocationGlobalRelative


# Set up option parsing to get connection string
import argparse
#Processing a Message
#Get server Resource
sqs = boto3.resource('sqs')
#Get the Queue
queue = sqs.get_queue_by_name(QueueName='My_Queue')

for message in queue.receive_messages(MessageAttributeNames=['Author']):
# Get the custom author message attribute if it was set
 author_text = ''
 if message.message_attributes is not None:
     author_name = message.message_attributes.get('Author').get('StringValue')
     if author_name:
         author_text = ' ({0})'.format(author_name)
#Split Message Body and store in variables
mess = message.body
mess = mess.split(';')
starting_latitude = float(mess[0])
starting_longitude = float(mess[1])
final_latitude = float(mess[2])
final_longitude = float(mess[3])
payload_weight = float(mess[4])
payload_id = float(mess[5])
#Print string so user knows what's going on
print('---Reading Queue Message---')
print('Starting Latitude:', mess[0])
print('Starting Longitude:', mess[1])
print('Final Latitude:', mess[2])
print('Final Longitude:', mess[3])
print('Payload Weight:', mess[4], 'lbs')
print('Payload ID:', mess[5])
print('---Finished Reading Queue Message---')
#Converting coordinates into LocationGlobal objects
user_loc = dronekit.LocationGlobal(starting_latitude,starting_longitude,0) #Where the user is located
dest_loc = dronekit.LocationGlobal(final_latitude,final_longitude,0) #Where the restaurant is located

#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
© Copyright 2015-2016, 3D Robotics.
simple_goto.py: GUIDED mode "simple goto" example (Copter Only)

Demonstrates how to arm and takeoff in Copter and how to navigate to points using Vehicle.simple_goto.

Full documentation is provided at http://python.dronekit.io/examples/simple_goto.html
"""


parser = argparse.ArgumentParser(description='Commands vehicle using vehicle.simple_goto.')
parser.add_argument('--connect',
                    help="Vehicle connection target string. If not specified, SITL automatically started and used.")
args = parser.parse_args()

connection_string = args.connect
sitl = None


# Start SITL if no connection string specified
if not connection_string:
    import dronekit_sitl
    sitl = dronekit_sitl.start_default(41.848460,-87.631830)
    connection_string = sitl.connection_string()


# Connect to the Vehicle
print('Connecting to vehicle on: %s' % connection_string)
vehicle = connect(connection_string, wait_ready=True)

def set_home_location(latitude,longitude,altitude):
    #Setting Home location
    charger_1_location = dronekit.LocationGlobal(41.848460,-87.631830,0) #Charger One located on the Man on the Bench Park
    vehicle.home_location = charger_1_location #Setting home to charger one location
    # Home Location set to Charger One
    print ("\n Home location: %s" % vehicle.home_location)

set_home_location(41.848460,-87.631830,0)

def arm_and_takeoff(aTargetAltitude):
    """
    Arms vehicle and fly to aTargetAltitude.
    """

    print("Basic pre-arm checks")
    # Don't try to arm until autopilot is ready
    while not vehicle.is_armable:
        print(" Waiting for vehicle to initialise...")
        time.sleep(1)

    print("Arming motors")
    # Copter should arm in GUIDED mode
    vehicle.mode = VehicleMode("GUIDED")
    vehicle.armed = True

    # Confirm vehicle armed before attempting to take off
    while not vehicle.armed:
        print(" Waiting for arming...")
        time.sleep(1)

    print("Taking off!")
    vehicle.simple_takeoff(aTargetAltitude)  # Take off to target altitude

    # Wait until the vehicle reaches a safe height before processing the goto
    #  (otherwise the command after Vehicle.simple_takeoff will execute
    #   immediately).
    while True:
        print(" Altitude: ", vehicle.location.global_relative_frame.alt)
        # Break and return from function just below target altitude.
        if vehicle.location.global_relative_frame.alt >= aTargetAltitude * 0.95:
            print("Reached target altitude")
            break
        time.sleep(1)


arm_and_takeoff(10)

print("Set default/target airspeed to 3")
vehicle.airspeed = 3

print("Going towards restaurant for 30 seconds ...")

vehicle.simple_goto(dest_loc, groundspeed=10)

while vehicle.location.global_frame != dest_loc:
    print(" Global Location: %s" % vehicle.location.global_frame)
    time.sleep(1)

print('Arrived at Restaurant!')

print("Moving towards user for 30 seconds ...")

vehicle.simple_goto(user_loc, groundspeed=10)

# sleep so we can see the change in map
time.sleep(30)

print("Returning to Launch")
vehicle.mode = VehicleMode("RTL")

# Close vehicle object before exiting script
print("Close vehicle object")
vehicle.close()

# Shut down simulator if it was started.
if sitl:
    sitl.stop()
