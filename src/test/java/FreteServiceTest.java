import org.example.FreteService;
import org.example.Pedido;
import org.junit.Test;

public class FreteServiceTest {
    private final FreteService fs = new FreteService();

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

}
