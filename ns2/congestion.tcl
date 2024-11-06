# NS2 script for congestion control and link state routing
# Define a simulator object
set ns [new Simulator]

# Open trace and NAM files
set tracefile [open congestion.tr w]
set namfile [open congestion.nam w]
$ns trace-all $tracefile
$ns namtrace-all $namfile

# Create four nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

# Set up links between nodes with bandwidth, delay, and queue types
$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail
$ns duplex-link $n2 $n3 1Mb 10ms DropTail
$ns duplex-link $n0 $n3 0.5Mb 20ms DropTail

# Configure routing protocol (Link State Routing)
$ns rtproto LS

# Setup TCP connection for congestion control
set tcp [new Agent/TCP]
$ns attach-agent $n0 $tcp

set sink [new Agent/TCPSink]
$ns attach-agent $n3 $sink

$ns connect $tcp $sink

# Attach an FTP application over TCP for data transfer
set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ns at 0.1 "$ftp start"

# End simulation after 5 seconds
$ns at 5.0 "finish"

# Finish procedure
proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exec nam congestion.nam &
    exit 0
}

$ns run
