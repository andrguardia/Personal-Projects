from dronekit import Vehicle, connect, VehicleMode, Command
from pymavlink import mavutil
from dronekit_sitl import SITL
import time

sitl = SITL()
sitl.download('copter', '3.3', verbose=True)
sitl_args = ['-I0', '--model', 'quad', '--home=41.83400609597797,-87.62711405754091,0,180']
sitl.launch(sitl_args, await_ready=True, restart=True)
connection_string = 'tcp:127.0.0.1:5760'
print("Connecting to vehicle on: %s" % connection_string)
vehicle = connect(connection_string, wait_ready=True, baud=57600)



cmds = vehicle.commands
cmds.clear()


def add_waypoint(lat,lon,altitude):
    cmd = Command(0,0,0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_WAYPOINT,
    0, 0, 0, 0, 0, 0,
    lat, lon, altitude)
    cmds.add(cmd)

def download_mission():
    cmds.download()
    cmds.wait_ready()
    cmds.upload()


add_waypoint(41.1,-87.1,10)
add_waypoint(41.2,-87.2,10)
add_waypoint(41.3,-87.3,10)
add_waypoint(41.3,-87.3,10)

download_mission()


print("Starting mission")
# Reset mission set to first (0) waypoint
vehicle.commands.next=0
# Set mode to AUTO to start mission
vehicle.mode = VehicleMode("AUTO")

while True:
    nextwaypoint=vehicle.commands.next
    print(vehicle.location.global_frame)
    if nextwaypoint==3: #Skip to next waypoint
        print('Skipping to Waypoint 2 when reach waypoint 1')
        vehicle.commands.next = 3
    if nextwaypoint==4: #Dummy waypoint - as soon as we reach waypoint 4 this is true and we exit.
        print("Exit 'standard' mission when start heading to final waypoint (5)")
        break;
    time.sleep(1)
