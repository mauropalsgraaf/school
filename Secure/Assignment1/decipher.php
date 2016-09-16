<?php
$toDecipher = 'dtucluclmlcuy xqlyqccmsqlqzobi dqplgudtlmlcuy xqlobi d!sbm tuolmxs!budty';
//this is a simple message encrypted with a simple cryptographic algorithm
$key = 'abcdefghijklmnopqrstuvwxyz! ';

$toDecipherLength = strlen($toDecipher);
$keyLength = strlen($key);

$result = '';

for($k = 1; $k < $keyLength; $k++) {
    for($i = 0; $i < $toDecipherLength; $i++) {
        for($j = 0; $j < $keyLength; $j++) {
            if ($toDecipher[$i] === $key[$j]) {
                $result = $result . $key[($j + $k) % $keyLength];
            }
        }
    }

    print_r($result . "\n");

    $result = '';
}

?>
