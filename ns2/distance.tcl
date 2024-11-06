# NS2 script for Distance Vector Routing (DV)
# Define a simulator object
set ns [new Simulator]

# Open trace and NAM files
set tracefile [open dv_routing.tr w]
set namfile [open dv_routing.nam w]
$ns trace-all $tracefile
$ns namtrace-all $namfile

# Create four nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

# Set up links between nodes with bandwidth and delay
$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail
$ns duplex-link $n2 $n3 1Mb 10ms DropTail
$ns duplex-link $n0 $n3 1Mb 15ms DropTail
$ns duplex-link $n0 $n2 0.5Mb 20ms DropTail

# Configure Distance Vector Routing
$ns rtproto DV

# End the simulation after 5 seconds
$ns at 5.0 "finish"

# Define finish procedure
proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exec nam dv_routing.nam &
    exit 0
}

# Run the simulation
$ns run
