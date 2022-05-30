package inter;

import symbols.Tipo;

public class For extends Instr {

    Instr instr;
    Expr expr;// se agrega una expr que nos servira para la condicion
    Instr instr1;//se agregan 3 instr nuevos 
    Instr instr2;
    Instr instr3;

    public For() {
        instr = null;
        expr = null;
        instr1 = null;//se dan los valores de null
        instr2 = null;
        instr3 = null;
    }

    public void inic(Instr s, Expr x, Instr s1, Instr s2, Instr s3) {
        expr = x;
        instr = s;
        instr1 = s1;//se les asigna los valores a la clase
        instr2 = s2;
        instr3 = s3;
        if (expr.tipo != Tipo.Bool) {
            expr.error("se requiere booleano en for");
        }
    }

    public void gen(int b, int a) {
        despues = a; // guarda la etiqueta a
        expr.salto(0, a);
        int etiqueta = nuevaEtiqueta(); // etiqueta para instr
        emitirEtiqueta(etiqueta);
        instr.gen(etiqueta, b);
        emitir("goto L" + b);
    }
}
