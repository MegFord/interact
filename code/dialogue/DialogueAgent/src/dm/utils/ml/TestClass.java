/**
 * 
 */
package dm.utils.ml;

import java.util.ArrayList;

/**
 * @author TimD
 *
 */
public class TestClass {

	public TestClass() {
		//default
	}
	
	public static void main(String[] args){
		StoredUtterance tempSU = new StoredUtterance();
		tempSU.addUtterance(0,"I would like to try this program");
		tempSU.addUtterance(1,"Can you be more clear in what you want?");
		tempSU.addUtterance(1,"What would you like me to do for you today?");
		tempSU.addUtterance(1,"These items are just what I am looking for thank you");
		tempSU.addUtterance(0,"Seems to me that our team has a good chance at winning this event");
		tempSU.addUtterance(0,"What can I say this is the greatest juice I have ever had");
		tempSU.addUtterance(0,"Now I know what you are going to say but hear me out");
		tempSU.addUtterance(1,"Sure I would like to know more about Tai Chi");
		tempSU.addUtterance(1,"Yes I think I am strong enough to that I dont need to do Tai Chi");
		tempSU.addUtterance(0,"What is so healthy about Tai Chi what makes you think I need it?");
		tempSU.addUtterance(0,"I would like to talk more about strength training and how it would help me");
		tempSU.addUtterance(1,"So what you are saying is that I can get stronger by doing Tai Chi");
		
		ArrayList<String> docs = new ArrayList<String>();
		docs.add("Physical exercise is any bodily activity that enhances or maintains physical fitness and overall health and wellness. It is performed for various reasons including strengthening muscles and the cardiovascular system, honing athletic skills, weight loss or maintenance, as well as for the purpose of enjoyment. Frequent and regular physical exercise boosts the immune system, and helps prevent the 'diseases of affluence' such as heart disease, cardiovascular disease, Type 2 diabetes and obesity.[1][2] It also improves mental health, helps prevent depression, helps to promote or maintain positive self-esteem, and can even augment an individual's sex appeal or body image, which is also found to be linked with higher levels of self-esteem.[3] Childhood obesity is a growing global concern[4] and physical exercise may help decrease some of the effects of childhood and adult obesity. Health care providers often call exercise the 'miracle' or 'wonder' drug—alluding to the wide variety of proven benefits that it providesPhysical exercise is any bodily activity that enhances or maintains physical fitness and overall health and wellness. It is performed for various reasons including strengthening muscles and the cardiovascular system, honing athletic skills, weight loss or maintenance, as well as for the purpose of enjoyment. Frequent and regular physical exercise boosts the immune system, and helps prevent the 'diseases of affluence' such as heart disease, cardiovascular disease, Type 2 diabetes and obesity.[1][2] It also improves mental health, helps prevent depression, helps to promote or maintain positive self-esteem, and can even augment an individual's sex appeal or body image, which is also found to be linked with higher levels of self-esteem.[3] Childhood obesity is a growing global concern[4] and physical exercise may help decrease some of the effects of childhood and adult obesity. Health care providers often call exercise the 'miracle' or 'wonder' drug—alluding to the wide variety of proven benefits that it provides");
		docs.add("Physical exercise is important for maintaining physical fitness and can contribute positively to maintaining a healthy weight, building and maintaining healthy bone density, muscle strength, and joint mobility, promoting physiological well-being, reducing surgical risks, and strengthening the immune system.");
		docs.add("Exercise reduces levels of cortisol,[12] which causes many health problems, both physical and mental.");
		docs.add("Frequent and regular aerobic exercise has been shown to help prevent or treat serious and life-threatening chronic conditions such as high blood pressure, obesity, heart disease, Type 2 diabetes, insomnia, and depression.[14][dead link] Endurance exercise before meals lowers blood glucose more than the same exercise after meals.[15] According to the World Health Organization, lack of physical activity contributes to approximately 17% of heart disease and diabetes, 12% of falls in the elderly, and 10% of breast cancer and colon cancer.[16]");
		docs.add("There is evidence that vigorous exercise (90–95% of VO2 Max) induces a greater degree of physiological cardiac hypertrophy than moderate exercise (40 to 70% of VO2 Max), but it is unknown whether this has any effects on overall morbidity and/or mortality.");
		docs.add("Some studies have shown that vigorous exercise executed by healthy individuals can increase opioid peptides (a.k.a. endorphins, naturally occurring opioids that in conjunction with other neurotransmitters are responsible for exercise-induced euphoria and have been shown to be addictive), increase testosterone and growth hormone,[18] effects that are not as fully realized with moderate exercise. More recent research[19][20] indicates that anandamide may play a greater role than endorphins in 'runner's high'. However, training at this[which?] intensity for long periods of time, or without proper warmup beforehand and cooldown afterwards, can lead to an increased risk of injury and overtraining.[citation needed]");
		docs.add("Both aerobic and anaerobic exercise work to increase the mechanical efficiency of the heart by increasing cardiac volume (aerobic exercise), or myocardial thickness (strength training). Such changes are generally beneficial and healthy if they occur in response to exercise.");
		docs.add("Not everyone benefits equally from exercise. There is tremendous variation in individual response to training; where most people will see a moderate increase in endurance from aerobic exercise, some individuals will as much as double their oxygen uptake, while others can never augment endurance.[21][22] However, muscle hypertrophy from resistance training is primarily determined by diet and testosterone.[23] This genetic variation in improvement from training is one of the key physiological differences between elite athletes and the larger population.[24][25] Studies have shown that exercising in middle age leads to better physical ability later in life.[26]");
		docs.add("Cardiovascular system");
		docs.add("The beneficial effect of exercise on the cardiovascular system is well documented. There is a direct relation between physical inactivity and cardiovascular mortality, and physical inactivity is an independent risk factor for the development of coronary artery disease. There is a dose-response relation between the amount of exercise performed from approximately 700 to 2000 kcal of energy expenditure per week and all-cause mortality and cardiovascular disease mortality in middle-aged and elderly populations. The greatest potential for reduced mortality is in the sedentary who become moderately active. Most beneficial effects of physical activity on cardiovascular disease mortality can be attained through moderate-intensity activity (40% to 60% of maximal oxygen uptake, depending on age). ... persons who modify their behavior after myocardial infarction to include regular exercise have improved rates of survival. ... Persons who remain sedentary have the highest risk for all-cause and cardiovascular disease mortality.[27]");
		docs.add("Immune system");
		docs.add("Although there have been hundreds of studies on exercise and the immune system, there is little direct evidence on its connection to illness. Epidemiological evidence suggests that moderate exercise has a beneficial effect on the human immune system; an effect which is modeled in a J curve. Moderate exercise has been associated with a 29% decreased incidence of upper respiratory tract infections (URTI), but studies of marathon runners found that their prolonged high-intensity exercise was associated with an increased risk of infection occurrence. However, another study did not find the effect. Immune cell functions are impaired following acute sessions of prolonged, high-intensity exercise, and some studies have found that athletes are at a higher risk for infections. The immune systems of athletes and nonathletes are generally similar. Athletes may have slightly elevated natural killer cell count and cytolytic action, but these are unlikely to be clinically significant.[28]");
		docs.add("Vitamin C supplementation has been associated with lower incidence of URTIs in marathon runners.[28]");
		docs.add("Biomarkers of inflammation such as C-reactive protein, which are associated with chronic diseases, are reduced in active individuals relative to sedentary individuals, and the positive effects of exercise may be due to its anti-inflammatory effects. The depression in the immune system following acute bouts of exercise may be one of the mechanisms for this anti-inflammatory effect.[28]");
		docs.add("Brain function");
		docs.add("A 2008 review of cognitive enrichment therapies (strategies to slow or reverse cognitive decline) concluded that 'physical activity, and aerobic exercise in particular, enhances older adults' cognitive function'.[29]");
		docs.add("In mice, exercise improves cognitive functioning via improvement of hippocampus-dependent spatial learning, and enhancement of synaptic plasticity and neurogenesis.[30] In addition, physical activity has been shown to be neuroprotective in many neurodegenerative and neuromuscular diseases.[31] For instance, it reduces the risk of developing dementia.[32] Furthermore, anecdotal evidence suggests that frequent exercise may reverse alcohol-induced brain damage.[33]");
		docs.add("There are several possibilities for why exercise is beneficial for the brain. Examples are as follows:");
		docs.add("increasing the blood and oxygen flow to the brain");
		docs.add("increasing growth factors that help create new nerve cells[34] and promote synaptic plasticity[35]");
		docs.add("increasing chemicals in the brain that help cognition, such as dopamine, glutamate, norepinephrine, and serotonin[36]");
		docs.add("Physical activity is thought to have other beneficial effects related to cognition as it increases levels of nerve growth factors, which support the survival and growth of a number of neuronal cells.[37]");
		docs.add("Depression");
		docs.add("A number of factors may contribute to depression including being overweight, low self-esteem, stress, and anxiety.[38] Endorphins act as a natural pain reliever and antidepressant in the body.[39] Endorphins have long been regarded as responsible for what is known as 'runner's high', a euphoric feeling a person receives from intense physical exertion.[40] However, recent research[19][20] indicates that anandamide may possibly play a greater role than endorphins in 'runner's high'. When a person exercises, levels of both circulating serotonin and endorphins are increased.[41] These levels are known to stay elevated even several days after exercise is discontinued, possibly contributing to improvement in mood, increased self-esteem, and weight management.[40] Exercise alone is a potential prevention method and/or treatment for mild forms of depression.[42] Research has also shown that when exercise is done in the presence of other people (familiar or not), it can be more effective in reducing stress than simply exercising alone.[43]");
		docs.add("Sleep");
		docs.add("A 2010 review of published scientific research suggested that exercise generally improves sleep for most people, and helps sleep disorders such as insomnia. The optimum time to exercise may be 4 to 8 hours before bedtime, though exercise at any time of day is beneficial, with the possible exception of heavy exercise taken shortly before bedtime, which may disturb sleep. There is, in any case, insufficient evidence to draw detailed conclusions about the relationship between exercise and sleep.[44]");
		docs.add("According to a 2005 study, exercise is the most recommended alternative to sleeping pills for resolving insomnia. Sleeping pills are more costly than to make time for a daily routine of staying fit, and may have dangerous side effects in the long run. Exercise can be a healthy, safe and inexpensive way to achieve more and better sleep.[45]");
		docs.add("Excessive exercise");
		docs.add("Too much exercise can be harmful. Without proper rest, the chance of stroke or other circulation problems increases,[46] and muscle tissue may develop slowly. Extremely intense, long-term cardiovascular exercise, as can be seen in athletes who train for multiple marathons, has been associated with scarring of the heart and heart rhythm abnormalities.[47][48][49]");
		docs.add("Inappropriate exercise can do more harm than good, with the definition of 'inappropriate' varying according to the individual. For many activities, especially running and cycling, there are significant injuries that occur with poorly regimented exercise schedules. Injuries from accidents also remain a major concern,[50] whereas the effects of increased exposure to air pollution seem only a minor concern.[51][52]");
		docs.add("In extreme instances, over-exercising induces serious performance loss. Unaccustomed overexertion of muscles leads to rhabdomyolysis (damage to muscle) most often seen in new army recruits.[53] Another danger is overtraining, in which the intensity or volume of training exceeds the body's capacity to recover between bouts.[54]");
		docs.add("Stopping excessive exercise suddenly can also create a change in mood. Feelings of depression and agitation can occur when withdrawal from the natural endorphins produced by exercise occurs.[citation needed] Exercise should be controlled by each body's inherent limitations. While one set of joints and muscles may have the tolerance to withstand multiple marathons, another body may be damaged by 20 minutes of light jogging. This must be determined for each individual.");
		docs.add("Too much exercise can also cause a woman to miss her period, a symptom known as amenorrhea.[55]");
		docs.add("Public health measures");
		docs.add("As of 2011 the effects of community wide interventions to increase exercise levels at the population level is unknown.[56] Signs that encourage the use of stairs, as well as community campaigns, may increase exercise levels.[57] The city of Bogotá, Colombia, for example, blocks off 113 kilometers (70 mi) of roads on Sundays and holidays to make it easier for its citizens to get exercise. These pedestrian zones are part of an effort to combat chronic diseases, including obesity.[58]");
		docs.add("Exercise trends");
		docs.add("Main article: Exercise trends");
		docs.add("Worldwide there has been a large shift towards less physically demanding work.[59] This has been accompanied by increasing use of mechanized transportation, a greater prevalence of labor saving technology in the home, and less active recreational pursuits.[59] Personal lifestyle changes however can correct the lack of physical exercise.");
		docs.add("Nutrition and recovery");
		docs.add("Proper nutrition is as important to health as exercise. When exercising, it becomes even more important to have a good diet to ensure that the body has the correct ratio of macronutrients whilst providing ample micronutrients, in order to aid the body with the recovery process following strenuous exercise.[60]");
		docs.add("History");
		docs.add("The benefits of exercise have been known since antiquity. Marcus Cicero, around 65 BC, stated: 'It is exercise alone that supports the spirits, and keeps the mind in vigor.'[61] However, the link between physical health and exercise (or lack of it) was only discovered in 1949 and reported in 1953 by a team led by Jerry Morris.[62][63] Dr. Morris noted that men of similar social class and occupation (bus conductors versus bus drivers) had markedly different rates of heart attacks, depending on the level of exercise they got: bus drivers had a sedentary occupation and a higher incidence of heart disease, while bus conductors were forced to move continually and had a lower incidence of heart disease.[63] This link had not previously been noted and was later confirmed by other researchers.");
		docs.add("In other animals");
		docs.add("Physical exercise has been shown to benefit a wide range of other mammals, as well as salmon, juvenile crocodiles, and at least one species of bird.[64] However, several studies have shown that lizards display no benefit from exercise, leading them to be termed 'metabolically inflexible'.[65][66]");
		docs.add("A number of studies of both rodents and humans have demonstrated that individual differences in both ability and propensity for exercise (i.e., voluntary exercise) have some genetic basis");
		
		MapTerms tempMT = new MapTerms();
		
		for(int i = 0; i < docs.size(); i++){
			tempMT.addMapTerms(docs.get(i));
		}
		
		for(String key: tempMT.getVocabulary().keySet()){
			tempMT.getVocabulary().get(key);
			System.out.printf("TFIDF: %.5f, Term Freq: %.5f, Term Count: %3.0f, Total Term Count: %4.0f, Doc Count: %2.0f, Total Doc Count: %2.0f, Term = %s\n", tempMT.getVocabulary().get(key).getTFIDF(), tempMT.getVocabulary().get(key).getTermFrequency(), tempMT.getVocabulary().get(key).getTermCount(), tempMT.getVocabulary().get(key).getTotalTermCount(), tempMT.getVocabulary().get(key).getDocCount(), Term.getTotalDocCount(), key);
		}
		System.out.println(docs.size());
		
		SentimentAnalysis tempSA = new SentimentAnalysis(tempMT, tempSU.getUtteranceList());
		
		System.out.println(tempSA.calculateSentAnalysis(7, "who is there"));
	}
}
