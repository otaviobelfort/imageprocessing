# Image Processing Design

## Implementação

### Dithering:
 - Limiar simples;
 - Limiar com modulação aleatória;
 - Limiar com modulação ordenada periódico aglomerado;
 - Limiar com modulação ordenada periódico disperso;
 - Limiar com modulação ordenada aperiódico;

### Morfologia matemática binária:
 - Erosão
 - Dilatação
 - Abertura
 - Fechamento
 - Borda interna
 - Borda externa

### Morfologia matemática monocromática:
 - Erosão
 - Dilatação
 - Abertura
 - Fechamento
 - Smoothing
 - Gradiente

## Class Image
### Metodos
* ``` Construtor ``` 
``` 
public Image(String path) 
public Image(int height, int width, int type)
public Image(int mat[][][])

``` 
* ``` Outros ``` 

```
public Image toGray() 
```

```
public Image toRGB()
```

```
public int getHeight()
```

```
public int getWidth()
```

```
 public int getChannel()
 ```


```
 public int[][][] getMatriz()
```

``` 
public void setMatriz(int[][][] mat) 
```

``` 
protected BufferedImage getBuffer()
```

``` 
public void save(String name, String typeOut)
```

``` 
 public void titleImage(String title)
```

``` 
public void titleImage()
```

* ``` Metedos -> Atividade PDI ``` 
- Fazer a Media de n imagens 29min

``` 
```
* ``` Class Histograma ``` 
#### imgEqualization() < call  process>
- calulateHistogram
- calulateHistogramAcum
- calulateTransfomation
- getImage



``` 
```

``` 

```

``` 
