# AlgaT
#### Progetto Algoritmi e Strutture Dati - 2019

Il progetto è stato testato solo su Linux.  
Per compilare ed eseguire AlgaT è stata utilizzata una versione Java Development Kit modificata e reperibile [qui](https://www.azul.com/downloads/zulu-community/), facendo particolare attenzione alla dicitura "JDK FX".  
La JDK utilizzata è versione 11.
- Recarsi da terminale nella directory dove è stata scaricata la versione corretta del JDK FX
- Lanciare,in ordine, i seguenti comandi
```bash
tar -xzvf zulu11.33.15-ca-fx-jdk11.0.4-linux_x64.tar.gz
sudo mv zulu11.33.15-ca-fx-jdk11.0.4-linux_x64 /usr/lib/jvm/
sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/zulu11.33.15-ca-fx-jdk11.0.4-linux_x64/bin/java 1212
```
- Digitare `sudo update-alternatives --config java` e selezionare la versione corretta, ovvero quella che è appena stata inserita.
- Infine, recarsi nella directory dove è presente il file AlgaT.jar e lanciare il comando `java -jar AlgaT.jar`

-------------------------------------
##### Autori
- ![#f03c15](https://placehold.it/15/f03c15/000000?text=+) [Stefano Faieta](https://github.com/stefanofa)  
- ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) [Damiano Scevola](https://github.com/lusvelt)
