package olakase.proyectoobjetos.objetos;

/**
 * Created by Admin on 28/09/2017.
 */

public class DatosRegistrar {
    private String emailR;
    private String contrasenaR;

    /**
     * @param emailR1
     * @param contrasenaR
     */
    public DatosRegistrar(String emailR1, String contrasenaR){
        /*Inicializacion de datos*/
        this.emailR = emailR1;
        this.contrasenaR = contrasenaR;
    }

    /**
     * @return emailR
     */
    public String getEmailR() {
        return emailR;
    }

    /**
     * @return contrasenaR
     */
    public String getContrasenaR() {
        return contrasenaR;
    }
}
