import java.util.*;


class Person {
	int age;
	String name;

	public Person(int age,String name){
		this.age=age;
		this.name=name;
	}

	public String toString() {
		return age+" "+name;
	}
}



public class Main {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int N=scan.nextInt();
		List<Person> person=new ArrayList<>();
		for(int i=0;i<N;i++) {
			int age=scan.nextInt();
			String name=scan.next();
			person.add(new Person(age,name));
		}

		Collections.sort(person,new Comparator<Person>() {
			public int compare(Person i1,Person i2) {
				if(i1.age>i2.age)
					return 1;
				else if(i1.age<i2.age)
					return -1;
				else 
					return 0;

			}
		});

		for (Person p: person) {
			System.out.println(p);
		}

	}
}
