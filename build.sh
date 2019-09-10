javac -d ./out --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -cp "$HOME/Public/json-simple-1.1.1.jar" $(find ./src/* | grep .java)
