from __future__ import print_function
print("------------------Start simulator (SITL)-----------------")
import dronekit_sitl
import dronekit
import time
import boto3
import time
from dronekit import connect, VehicleMode
import time
from dronekit import connect, VehicleMode, LocationGlobalRelative
'''
Processing Messages
Getting server Resources
'''
sqs_resource = boto3.resource('sqs')
sqs_client = boto3.client('sqs')
#Get the Queue
queue = sqs_resource.get_queue_by_name(QueueName='Delivery_Line')
def read_message():
    #Split Message Body and store in variables
    message = sqs_client.receive_message(
    QueueUrl='https://sqs.us-east-2.amazonaws.com/548002151864/Delivery_Line',
)

    message1 = message.get("Messages")
    message1 = message1[0]
    message_body = message1.get("Body")
    receipt_handle = message1.get("ReceiptHandle")
    mess = message_body.split(';')
    starting_latitude = mess[0]
    starting_longitude = mess[1]
    final_latitude = mess[2]
    final_longitude = mess[3]
    payload_weight = mess[4]
    payload_id = mess[5]
    #Print string so user knows what's going on

    print('---Reading Queue Message---')
    print('Starting Latitude:', mess[0])
    print('Starting Longitude:', mess[1])
    print('Final Latitude:', mess[2])
    print('Final Longitude:', mess[3])
    print('Payload Weight:', mess[4], 'lbs')
    print('Payload ID:', mess[5])
    message = sqs_client.delete_message(
    QueueUrl='https://sqs.us-east-2.amazonaws.com/548002151864/Delivery_Line',
    ReceiptHandle=receipt_handle
    )

    print('Message Read!')
rep = 1 #Initialize response, value will be updated once loop starts
def remaining_messages():
    response = sqs_client.get_queue_attributes(
    QueueUrl='https://sqs.us-east-2.amazonaws.com/548002151864/Delivery_Line',
    AttributeNames=[
        'ApproximateNumberOfMessages'
        ]
    )
    response = response.get("Attributes")
    rep = int(response.get("ApproximateNumberOfMessages"))
    return rep
response_init = remaining_messages()
for rep in range(0,response_init):
    rep = remaining_messages()
    read_message()
    time.sleep(1)


#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
© Copyright 2015-2016, 3D Robotics.
simple_goto.py: GUIDED mode "simple goto" example (Copter Only)

Demonstrates how to arm and takeoff in Copter and how to navigate to points using Vehicle.simple_goto.

Full documentation is provided at http://python.dronekit.io/examples/simple_goto.html
"""




# Set up option parsing to get connection string
import argparse
parser = argparse.ArgumentParser(description='Commands vehicle using vehicle.simple_goto.')
parser.add_argument('--connect',
                    help="Vehicle connection target string. If not specified, SITL automatically started and used.")
args = parser.parse_args()

connection_string = args.connect
sitl = None


# Start SITL if no connection string specified
if not connection_string:
    import dronekit_sitl
    sitl = dronekit_sitl.start()
    connection_string = sitl.connection_string()


# Connect to the Vehicle
print('Connecting to vehicle on: %s' % connection_string)
vehicle = connect(connection_string, wait_ready=True)


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

print("Set default/target airspeed to 20mph")
vehicle.airspeed = 20

print("Going towards first point for 30 seconds ...")
point1 = LocationGlobalRelative(-35.361354, 149.165218, 20)

while vehicle.location.global_frame != point1:
    vehicle.simple_goto(point1)
    print(vehicle.location.global_frame)

print('Arrived at Pickup Location')
