package start;

public class ReleaseRecommendation implements Recommendation {

	public void giveRecommendation() {
		System.out.println(this.getClass().getName());
	}
}