$a = $args.length
$root_dir = Join-Path (Split-Path $MyInvocation.MyCommand.Path -Parent) ..
$jar_dir = Join-Path $root_dir target

$jar = Join-Path $jar_dir codejam-solutions-0.0.1-SNAPSHOT-jar-with-dependencies.jar
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

java -jar $jar $imp_class $input $output