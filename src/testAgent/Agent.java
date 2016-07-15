package testAgent;

public class Agent {

	private String agentBreed;
	private String previousBreed;
	private String postPreviousBreed;
	private double policyID;
	private int age;
	private int socialGrade;
	private int paymentAtPurchase;
	private float attributeBrand;
	private float attributePrice;
	private float attributePromotions;
	private int autoRenew;
	private int inertiaForSwitch;

	/**
	 * Class constructor: Initialize the attributes to empty Strings or 0
	 */
	public Agent() {
		this.postPreviousBreed = "";
		this.previousBreed = "";
		this.agentBreed = "";
		this.policyID = 0;
		this.age = 0;
		this.socialGrade = 0;
		this.paymentAtPurchase = 0;
		this.attributeBrand = 0;
		this.attributePrice = 0;
		this.attributePromotions = 0;
		this.autoRenew = 0;
		this.inertiaForSwitch = 0;
		
	}

	/**
	 * Clone the object and returns a copy
	 */
	public Agent clone() {
		Agent ag = new Agent();
		ag.setAgentBreedClone(this.agentBreed);
		ag.setPreviousBreed(this.previousBreed);
		ag.setPostPreviousBreed(this.postPreviousBreed);
		ag.setPolicyID(this.policyID);
		ag.setAge(this.age);
		ag.setSocialGrade(this.socialGrade);
		ag.setPaymentAtPurchase(this.paymentAtPurchase);
		ag.setAttributeBrand(this.attributeBrand);
		ag.setAttributePrice(this.attributePrice);
		ag.setAttributePromotions(this.attributePromotions);
		ag.setAutoRenew(this.autoRenew);
		ag.setInertiaForSwitch(this.inertiaForSwitch);
		
		return ag;
	}
	
	/**
	 * Print the agent into the Console
	 */
	public void printAgent() {
		String result = "";
		
		result += agentBreed + ", ";
		result += policyID + ", ";
		result += age + ", ";
		result += socialGrade + ", ";
		result += paymentAtPurchase + ", ";
		result += attributeBrand + ", ";
		result += attributePrice + ", ";
		result += attributePromotions + ", ";
		result += autoRenew + ", ";
		result += inertiaForSwitch;
		
		System.out.println(result);
	}
	
	/*----------------------------------------------------------------*/
	/*-----------             GETTERS & SETTERS             ----------*/
	/*----------------------------------------------------------------*/

	public String getAgentBreed() {
		return agentBreed;
	}

	public void setAgentBreed(String agentBreed) {
		this.postPreviousBreed = this.previousBreed;
		this.previousBreed = this.agentBreed;
		this.agentBreed = agentBreed;
	}
	
	public void setAgentBreedClone(String agentBreed) {
		this.agentBreed = agentBreed;
	}
	
	public String getPreviousBreed() {
		return previousBreed;
	}

	public void setPreviousBreed(String previousBreed) {
		this.previousBreed = previousBreed;
	}
	
	public String getPostPreviousBreed() {
		return postPreviousBreed;
	}

	public void setPostPreviousBreed(String postPreviousBreed) {
		this.postPreviousBreed = postPreviousBreed;
	}

	public double getPolicyID() {
		return policyID;
	}

	public void setPolicyID(double policyID) {
		this.policyID = policyID;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSocialGrade() {
		return socialGrade;
	}

	public void setSocialGrade(int socialGrade) {
		this.socialGrade = socialGrade;
	}

	public int getPaymentAtPurchase() {
		return paymentAtPurchase;
	}

	public void setPaymentAtPurchase(int paymentAtPurchase) {
		this.paymentAtPurchase = paymentAtPurchase;
	}

	public float getAttributeBrand() {
		return attributeBrand;
	}

	public void setAttributeBrand(float attributeBrand) {
		this.attributeBrand = attributeBrand;
	}

	public float getAttributePrice() {
		return attributePrice;
	}

	public void setAttributePrice(float attributePrice) {
		this.attributePrice = attributePrice;
	}

	public float getAttributePromotions() {
		return attributePromotions;
	}

	public void setAttributePromotions(float attributePromotions) {
		this.attributePromotions = attributePromotions;
	}

	public int getAutoRenew() {
		return autoRenew;
	}

	public void setAutoRenew(int autoRenew) {
		this.autoRenew = autoRenew;
	}

	public int getInertiaForSwitch() {
		return inertiaForSwitch;
	}

	public void setInertiaForSwitch(int inertiaForSwitch) {
		this.inertiaForSwitch = inertiaForSwitch;
	}

}
