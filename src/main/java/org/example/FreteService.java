package org.example;

public class FreteService {

    private static final double BASE_POR_KM = 1.20;
    private static final double EXCESSO_PESO_LIMITE_KG = 5.0;
    private static final double EXCESSO_PESO_TAXA_POR_KG = 2.0;
    private static final double TAXA_FRAGIL = 15.0;
    private static final double MULTIPLICADOR_EXPRESSO = 1.5;
    private static final double MINIMO = 10.0;
    private static final double MAXIMO = 300.0;

    public double calcularFrete(Pedido pedido){
        // Passo 1: Validar dados de entrada
        validar(pedido);

        //Implementar...
        return 0;
    }

    private  void validar (Pedido pedido){
        if (pedido.getDistanciaKm() < 0) throw new
                IllegalArgumentException("Distância não pode ser negativa");
        if (pedido.getPesoKg() < 0)
            throw new IllegalArgumentException("O peso não pode ser negativo");
    }

    public static void main(String[] args) {
        Pedido padrao = new Pedido(150.0, 30.0, 5.5,
                false, false);
        Pedido fragil = new Pedido(80.0, 10.0, 8.0,
                true, false);
        Pedido expresso = new Pedido(90.0, 5.0, 2.0,
                false, true);
        Pedido gratis = new Pedido(250.0, 15.0, 3.0,
                false, false);

        FreteService fs = new FreteService();

        System.out.println("Frete Padrão: " + fs.calcularFrete(padrao));
        System.out.println("Frete Frágil: " + fs.calcularFrete(fragil));
        System.out.println("Frete Expresso: " + fs.calcularFrete(expresso));
        System.out.println("Frete Grátis: " + fs.calcularFrete(gratis));
    }
}
