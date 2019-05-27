package start;

public class FactoryPatternDemo {

	public static void main(String[] args) {
		RecommendationFactory shapeFactory = new RecommendationFactory();
		Recommendation genres = shapeFactory.getShape("GENRES");
		genres.giveRecommendation();
		Recommendation trends = shapeFactory.getShape("TRENDS");
		trends.giveRecommendation();
		Recommendation release = shapeFactory.getShape("RELEASE");
		release.giveRecommendation();
	}
}