package start;

public class RecommendationFactory {
	
	   //use getShape method to get object of type shape 
	   public Recommendation getShape(String shapeType){
	      if(shapeType == null){
	         return null;
	      }		
	      if(shapeType.equalsIgnoreCase("GENRES")){
	         return new GenresRecommendation();
	         
	      } else if(shapeType.equalsIgnoreCase("TRENDS")){
	         return new TrendsRecommendation();
	         
	      } else if(shapeType.equalsIgnoreCase("RELEASE")){
	         return new ReleaseRecommendation();
	      }
	      
	      return null;
	   }
	}