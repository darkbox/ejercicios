package tools;

/**
 * 
 * Contiene expresiones regulares definidas en variables constantes.
 *
 * @author Rafael García Maliga
 * @version 1.0
 * 
 */
public class Regex {
	/*
	 *****************************************************************************
	 *                         -EXPRESIONES REGULARES-
	 *****************************************************************************
	 */
	/*
	 * Nota: Todos las \ tienen que estar escapadas
	 * por ejemplo \. se convierte en \\.
	 */
	/**
	 * @brief REGEX_MAC
	 * XX:XX:XX:XX:XX:XX
	 * XX-XX-XX-XX-XX-XX
	 */
	public static final String REGEX_MAC = "^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$"; // Con minusculas ^([0-9a-fA-F]{2}[:-]){5}([0-9a-fA-F]{2})$
	/**
	 * @brief REGEX_IP
	 * 000.000.000.000
	 */
	public static final String REGEX_IP = "^(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
	/**
	 * @brief REGEX_EMAIL
	 * xxxxxx@xxxxx.xx
	 * xxx.xx@xx.xx.xx
	 */
	public static final String REGEX_EMAIL = "^[a-z0-9]+(\\.[a-z0-9]+)*@[a-z0-9-_]+(\\.[a-z0-9-_]+)*(\\.([a-z])+)$";
	/**
	 * @brief REGEX_CP
	 * 
	 * Código postal válido para España
	 * 
	 * 00000
	 */
	public static final String REGEX_CP = "^([0-4][1-9]|[5][0-2])([0-9][1-9][0]|[0-9]{2}[1-9])$"; 
	/**
	 * @brief REGEX_NCC
	 * 
	 * Validar número de cuenta corriente:
	 * - El código del banco al que pertenece la cuenta (4 dígitos)
	 * - El código de la sucursal en el que se abrio la cuenta (4 dígitos)
	 * - Un número de control, llamado dígito de control, que impide errores de teclado (2 dígitos)
	 * - Y por último, el número de cuenta (10 dígitos)
	 * 
	 * 0000-0000-00-0000000000
	 */
	public static final String REGEX_NCC = "^[0-9]{4}-[0-9]{4}-[0-9]{2}-[0-9]{10}$";
	/**
	 * @brief REGEX_NIF
	 * 
	 * DNI/NIF de 8 dígitos más letra de control
	 * 
	 * 00000000X
	 */
	public static final String REGEX_NIF = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$";
	/**
	 * @brief REGEX_SAFE_PASS
	 * 
	 * La contraseña tendrá una longitud de al menos seis caracteres.
	 * b. La contraseña contendrá caracteres de tres de las cuatro categorías siguientes:
	 * i. Caracteres en mayúsculas (A–Z)
	 * ii. Caracteres en minúsculas (a–z)
	 * iii. Base de 10 dígitos (0–9)
	 * iv. Caracteres no alfanuméricos (por ejemplo: !, $, #, o %)
	 * 
	 */
	public static final String REGEX_SAFE_PASS = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[-_.@#$%]).{6,})$";
	
	public static final String REGEX_URL = "^(ht|f)tp(s?)://[0-9a-zA-Z]([-.w]*[0-9a-zA-Z])*(:(0-9)*)*(/?)( [a-zA-Z0-9-.?,'/\\+&%$#_]*)?$";
	
	public static final String REGEX_HTML_TAG = "(<(/?[^>]+)>)";
	
	public static final String REGEX_TELF = "^[0-9]{2,3}-? ?[0-9]{6,7}$";
	
	public static final String REGEX_DATE = "^d{1,2}/d{1,2}/d{2,4}$";
	
	public static final String REGEX_CURRENCY = "^(-)?d+(.dd)?$";
	
	
}
