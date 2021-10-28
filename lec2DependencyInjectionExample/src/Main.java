import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{

public static void main(String [] args){

ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


ClassSeven classSeven = context.getBean("classSeven",ClassSeven.class);

System.out.println(classSeven.getInformation());
System.out.println(classSeven.getVariable1());

System.out.println(classSeven.getExtraInformationForAnother());
System.out.println(classSeven.getVariable2());

context.close();

}

}
