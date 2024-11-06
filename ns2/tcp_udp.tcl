# NS2 script for analyzing TCP and UDP performance

# Define a simulator object
set ns [new Simulator]

# Open trace and NAM files
set tracefile [open tcp_udp.tr w]
set namfile [open tcp_udp.nam w]
$ns trace-all $tracefile
$ns namtrace-all $namfile

# Create four nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

# Set up links with bandwidth, delay, and queue type
$ns duplex-link $n0 $n2 2Mb 10ms DropTail
$ns duplex-link $n1 $n2 2Mb 10ms DropTail
$ns duplex-link $n2 $n3 1Mb 20ms DropTail

# Configure TCP connection
set tcp [new Agent/TCP]
$ns attach-agent $n0 $tcp

set tcp_sink [new Agent/TCPSink]
$ns attach-agent $n3 $tcp_sink
$ns connect $tcp $tcp_sink

# Attach an FTP application to TCP for data transfer
set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ns at 0.1 "$ftp start"

# Configure UDP connection
set udp [new Agent/UDP]
$ns attach-agent $n1 $udp

set null_sink [new Agent/Null]
$ns attach-agent $n3 $null_sink
$ns connect $udp $null_sink

# Attach a CBR (Constant Bit Rate) application to UDP for data transfer
set cbr [new Application/Traffic/CBR]
$cbr set packetSize_ 512
$cbr set rate_ 1Mb
$cbr attach-agent $udp
$ns at 0.2 "$cbr start"

# End simulation after 5 seconds
$ns at 5.0 "finish"

# Define finish procedure
proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exec nam tcp_udp.nam &
    exit 0
}

# Run the simulation
$ns run
