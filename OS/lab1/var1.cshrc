echo "Тип файла: "
read n


if ! [ -d "$n" ]; then
mkdir "$n"
fi

for i in *.$n
do 
  mv "$i" "$n"
done








