El archivo proyectoOptimizacion.rar contiene un proyecto del IDE eclipse para Windows usando la versión 1.8 de Java.

1. Se deben agregar las dos librerías lpsolve55.dll y lpsolve55j.dll a la ruta "C:\Windows\System32", reiniciar el computador una vez hecho esto. Las librerías se pueden obtener del siguiente URL:
https://github.com/datumbox/lpsolve/tree/master/lib
(Escoger la arquitectura adecuada a su sistema operativo).

2. Descomprimir el archivo "proyectoOptimizacion.rar", he importarlo al IDE Eclipse "File->Open proyect from File System".

3. Volver a agregar la librería lpsolve55.jar "File->Properties->Java Build Path->Libraries->Colocar puntero en Lpsolve55.jar-> Edit-> buscar el archivo que está en la carpeta del proyecto, seleccionarlo->abrir".

Y ya se puede ejecutar el proyecto "Run->Run" o presionando "Ctrl+f11".

Para usar se debe hacer lo siguiente "Archivo->Abrir-> Seleccionar alguno de los archivos de prueba que están en la carpeta pruebas dentro de la carpeta proyecto" luego de esto se da clic en el botón "Solucionar" y si se quieren más detalles dar clic en el botón "Detalles".
