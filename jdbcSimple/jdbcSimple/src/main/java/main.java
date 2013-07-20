import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class main {
	private static JdbcTemplate jdbcTemplateObject;
	
	 public static void main(String[] args) {
	      ApplicationContext context = 
	             new ClassPathXmlApplicationContext("app-context.xml");
	      DataSource ds = (DataSource) context.getBean("dataSource");
	      jdbcTemplateObject = new JdbcTemplate(ds);
	    //  String SQL = "insert into Student (name, age) values ('Lon', '9')";
	      String SQL = "insert into Student (name, age) values (?, ?)"; 
	      String name="ko";
	      Integer age=9;
	     jdbcTemplateObject.update(SQL,  new Object[]{name, age} );
	     // System.out.println("Created Record Name = " + name + " Age = " + age);
	 }

}
