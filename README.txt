El archivo proyectoOptimizacion.rar contiene un proyecto del IDE eclipse para Windows usando la versi�n 1.8 de Java.

1. Se deben agregar las dos librer�as lpsolve55.dll y lpsolve55j.dll a la ruta "C:\Windows\System32", reiniciar el computador una vez hecho esto. Las librer�as se pueden obtener del siguiente URL:
https://github.com/datumbox/lpsolve/tree/master/lib
(Escoger la arquitectura adecuada a su sistema operativo).

2. Descomprimir el archivo "proyectoOptimizacion.rar", he importarlo al IDE Eclipse "File->Open proyect from File System".

3. Volver a agregar la librer�a lpsolve55.jar "File->Properties->Java Build Path->Libraries->Colocar puntero en Lpsolve55.jar-> Edit-> buscar el archivo que est� en la carpeta del proyecto, seleccionarlo->abrir".

Y ya se puede ejecutar el proyecto "Run->Run" o presionando "Ctrl+f11".

Para usar se debe hacer lo siguiente "Archivo->Abrir-> Seleccionar alguno de los archivos de prueba que est�n en la carpeta pruebas dentro de la carpeta proyecto" luego de esto se da clic en el bot�n "Solucionar" y si se quieren m�s detalles dar clic en el bot�n "Detalles".
