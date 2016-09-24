<?php
$toDecipher = 'xqm drwieiwrqypndvi wjkndnrlvftaimddmaliewdrqyvxznhig!ayxxk!eylrgieukxvrxqq';
//this is a simple message encrypted with an improved cryptographic algorithm
$key = 'abcdefghijklmnopqrstuvwxyz! ';

$toDecipherLength = strlen($toDecipher);
$keyLength = strlen($key);

$result = '';

$a1 = 0;
$a2 = 0;

while ($a1 < 29) {

    for($i = 0; $i < $toDecipherLength; $i++) {
        for($j = 0; $j < $keyLength; $j++) {
            if ($toDecipher[$i] === $key[$j]) {

                if ($i % 2 == 0) {
                    $result = $result . $key[($j + $a2) % $keyLength];
                } else {
                    $result = $result . $key[($j + $a1) % $keyLength];
                }
            }
        }
    }

    print_r($result . "\n");
    $result = '';

    if ($a2 < 29) {
        $a2++;
    } else {
        $a2 = 0;
        $a1++;
    }
}

?>
