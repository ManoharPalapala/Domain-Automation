import java.util.HashMap;
import java.util.Map;

public class rough {

	Map<String,String> locationMap = new HashMap<String,String>();

	
	public static void main(String[] args) {
		rough r = new rough();
		r.demo();
	}
	

	public void demo() {
		
		
		String location = "IN, US, 32601"; //pass the location value here
		String[] keywordsList = {"Waste Management jobs IN IN","jobs in Gainesville FL","hirings at Gainesville FL FL"}; //pass the keywords here
		
		String[] splitLocation = location.split(" ");
		
		if(splitLocation.length>3) {
			locationMap.put("City", splitLocation[0].replace(",",""));
			locationMap.put("StateCode", splitLocation[1].replace(",",""));
			locationMap.put("CountryCode", splitLocation[2].replace(",",""));
			locationMap.put("PostalCode", splitLocation[3]);
		}
//		}else if(splitLocation.length<=3 && splitLocation[0].length()<2){
//			locationMap.put("StateCode", splitLocation[1].replace(",",""));
//			locationMap.put("CountryCode", splitLocation[2].replace(",",""));
//			locationMap.put("PostalCode", splitLocation[3]);
//		}
		
		
		for(String keyword:keywordsList) {
			
		System.out.println(wordCounter(keyword,locationMap.get("City"),locationMap.get("StateCode"))>1);	
		}
	}
	
	
	public int wordCounter(String keyword, String city, String stateCode) {
		
		int initialCount=0;
		int count=0;
			
		for(String e:keyword.split(" ")) {
				
			if(keyword.contains(city) && keyword.contains(stateCode)) {
				if(e.equals(city) || e.equals(stateCode)) {
					initialCount++;
						if(initialCount>2) {count=initialCount;}
				}
			}
				else if(e.equals(city) || e.equals(stateCode)) {
					count++;
				}
				}
			
		return count;
	
}
	

	





















}
