$a = $args.length
$root_dir = Join-Path (Split-Path $MyInvocation.MyCommand.Path -Parent) ..
$jar_dir = Join-Path $root_dir target

if ($a -ne 2 -and $a -ne 3) {
    echo "Incorrect number of input parameters: $a"
    break
}

$jar = Join-Path $jar_dir codejam-solutions-0.0.1-SNAPSHOT.jar
$main = "com.ddamiani.codejam.Runner"
$imp_class = $args[0]
$input = $args[1]
$output = $args[2]

if(!(Test-Path $jar)) {
    echo "Jar file not found: $jar"

    echo "Attempting to build it..."
    pushd $root_dir
    mvn clean package
    popd

    if(!(Test-Path $jar)) {
        echo "Jar is still not there. Exitting..."
        break
    }
}

java -cp $jar $main $imp_class $input $output