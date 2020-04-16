import java.sql.*;

public class Main {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://192.168.0.14/cesa_facturacion", "usuario", "usuario");
			System.out.println("Conexión realizada\n");
			
			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM FACT_PROV";
			ResultSet resul = sentencia.executeQuery(sql);
			System.out.println("Consulta tabla FACT_PROV correcta");
			
			sql = "SELECT * FROM PROV_COMP";
			resul = sentencia.executeQuery(sql);
			System.out.println("Consulta tabla PROV_COMP correcta");
			
			
			System.out.println("\nCerrando conexión");
			resul.close();
			sentencia.close();
			conexion.close();

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
