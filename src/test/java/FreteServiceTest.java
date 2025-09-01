import jdk.jshell.PersistentSnippet;
import org.example.FreteService;
import org.example.Pedido;
import org.junit.Assert;
import org.junit.Test;

public class FreteServiceTest {
    private final FreteService fs = new FreteService();
    private static final double DELTA = 0.0001;

    @Test (expected = IllegalArgumentException.class)
    public void validarDeveRejeitarDistanciaNegativa(){
        fs.calcularFrete(new
                Pedido(100.0, -1,2,false,false));
    }

    @Test(expected =  IllegalArgumentException.class)
    public void validarPesoNegativo(){
        fs.calcularFrete(new
                Pedido(50.0, 10.0, -5, true,true));
    }

    @Test
    public void validarBasePorDistanciaPercorrida(){
        Pedido p = new Pedido(50.0,10.0, 3.0, false, false);
        //10km -> 1.2 -> minimo é 10, 12 reais
        Assert.assertEquals(12.0, fs.calcularFrete(p), DELTA);
    }

    @Test
    public void validarExcessoPesoAcimaLimite(){
        Pedido pNoLimite = new Pedido(50.0, 10.0,
                5.0, false, false);
        Pedido pAcima = new Pedido(50.0, 10.0,
                5.5, false, false);
        // No limite, 10*1.2 = 12
        Assert.assertEquals(12.0, fs.calcularFrete(pNoLimite), DELTA);
        // Peso foi mais que 5kg -> 10*1.2 = 12 + 0.5*2 -> 12 + 1 => 13
        Assert.assertEquals(13.0, fs.calcularFrete(pAcima), DELTA);
    }

    @Test
    public void validarTaxaFragil(){
        Pedido p = new Pedido(80.0, 10.0, 6.0, true, false);
        // 10 * 1.2 = 12 + excesso (1kg * 2) = 2 = 12 + 2 => 14 => + fragil 15 => 29.00 reais
        Assert.assertEquals(29.00, fs.calcularFrete(p), DELTA);
    }

    @Test
    public void validarFreteExpresso(){
        Pedido p = new Pedido(90.0, 5.0, 2.0, false, true);
        // 1.2 * 5 => 6 => * 1,5 => 9.00 (> 10 minimo) => 10 reais
        Assert.assertEquals(10.0, fs.calcularFrete(p), DELTA);
    }

    @Test
    public void freteGratis(){
        Pedido gratis = new Pedido(220.0, 20.0, 2.0,
                false, false);
        Assert.assertEquals(0.0, fs.calcularFrete(gratis), DELTA);
    }


}
