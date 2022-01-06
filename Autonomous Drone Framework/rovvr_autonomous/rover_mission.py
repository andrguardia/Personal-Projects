from dronekit import connect, VehicleMode, LocationGlobalRelative
# Get the set of commands from the vehicle
connection_string = 'tcp:127.0.0.1:5763'
vehicle = connect(connection_string, wait_ready=True)


# Set mode to AUTO to start mission
vehicle.mode = VehicleMode("AUTO")
