package analizadorLexico; // Archivo AnalizadorLexico.java

import symbols.Tipo;
import java.io.*;
import java.util.*;

public class AnalizadorLexico {

    public static int linea = 1;
    char preanalisis = ' ';
    Map<String, Palabra> palabras = new HashMap();

    void reservar(Palabra w) {
        palabras.put(w.lexema, w);
    }
    public AnalizadorLexico() {
        reservar(new Palabra("if", Etiqueta.IF));
        reservar(new Palabra("else", Etiqueta.ELSE));
        reservar(new Palabra("while", Etiqueta.WHILE));
//FOR        
        reservar(new Palabra("for", Etiqueta.FOR));
        reservar(new Palabra("do", Etiqueta.DO));
        reservar(new Palabra("break", Etiqueta.BREAK));
        reservar(Palabra.True);
        reservar(Palabra.False);
        reservar(Tipo.Int);
        reservar(Tipo.Char);
        reservar(Tipo.Bool);
        reservar(Tipo.Float);
    }
    //NUEVO
    public void print() {
        System.out.println("Linea:" + linea);
        for (String keys : palabras.keySet()) {
            System.out.println(keys + ":" + palabras.get(keys));
        }
    }

    void readch() throws IOException {
        preanalisis = (char) System.in.read();
    }

    boolean readch(char c) throws IOException {
        readch();
        if (preanalisis != c) {
            return false;
        }
        preanalisis = ' ';
        return true;
    }

    public Token explorar() throws IOException {
        for (;; readch()) {
            if (preanalisis == ' ' || preanalisis == '\t') {
                continue;
            } else if (preanalisis == '\n') {
                linea = linea + 1;
            } else if (preanalisis == '/') { // encontramos una coincidencia
                readch(); // leemos el siguiente char 
                if (preanalisis == '/') { // si el siguiente char es también un '/'
                    while (1 == 1) { // entramos en un bucle while
                        readch(); // ignoramos los siguientes char…
                        if (preanalisis == '\n') { // si llegamos al final de la línea
                            linea++; // pasamos a la siguiente línea
                            break; // salimos del bucle.
                        }
                    }
                } // si el siguiente char no era un '/' el explorador continua...
            }
            else {
                break;
            }
        }
        switch (preanalisis) {
            case '&':
                if (readch('&')) {
                    return Palabra.and;
                } else {
                    return new Token('&');
                }
            case '|':
                if (readch('|')) {
                    return Palabra.or;
                } else {
                    return new Token('|');
                }
            case '=':
                if (readch('=')) {
                    return Palabra.eq;
                } else {
                    return new Token('=');
                }
            case '!':
                if (readch('=')) {
                    return Palabra.ne;
                } else {
                    return new Token('!');
                }
            case '<':
                if (readch('=')) {
                    return Palabra.le;
                } else {
                    return new Token('<');
                }
            case '>':
                if (readch('=')) {
                    return Palabra.ge;
                } else {
                    return new Token('>');
                }
        }
        if (Character.isDigit(preanalisis)) {
            int v = 0;
            do {
                v = 10 * v + Character.digit(preanalisis, 10);
                readch();
            } while (Character.isDigit(preanalisis));
            if (preanalisis != '.') {
                return new Num(v);
            }
            float x = v;
            float d = 10;
            for (;;) {
                readch();
                if (!Character.isDigit(preanalisis)) {
                    break;
                }
                x = x + Character.digit(preanalisis, 10) / d;
                d = d * 10;
            }
            return new Real(x);
        }
        if (Character.isLetter(preanalisis)) {
            StringBuffer b = new StringBuffer();
            do {
                b.append(preanalisis);
                readch();
            } while (Character.isLetterOrDigit(preanalisis));
            String s = b.toString();
            Palabra w = (Palabra) palabras.get(s);
            if (w != null) {
                return w;
            }
            w = new Palabra(s, Etiqueta.ID);
            palabras.put(s, w);
            return w;
        }
        Token tok = new Token(preanalisis);
        preanalisis = ' ';
        return tok;
    }
}
