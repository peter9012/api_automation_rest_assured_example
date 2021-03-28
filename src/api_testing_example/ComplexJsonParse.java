package api_testing_example;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		String firstCourseTitle = js.get("courses[0].title");
		System.out.println(firstCourseTitle);
		
		for(int i = 0; i < count; i++) {
			System.out.println(js.get("courses["+i+"].title").toString());
			System.out.println(js.get("courses["+i+"].price").toString());
			String courseTitles = js.get("courses["+i+"].title");
			if (courseTitles.equalsIgnoreCase("Selenium Python")) {
				int copies = js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
	}
}
