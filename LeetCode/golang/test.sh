if [ -n "$1" ]; then
    # TODO: adding support for more inputs.
    go test -v ./$1
else
    echo "Usage: test.sh <folder_name>"
fi
