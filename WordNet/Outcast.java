public class Outcast 
{ 
	private WordNet wordNet;
	public Outcast(WordNet wordnet)  
	{ 
		this.wordNet = wordnet;
	} 
	public String outcast(String[] nouns)
	{ 
		int result=0;
		int maxDistance = -1;
		if(nouns.length == 2) return nouns[0];
		for( int i = 0; i < nouns.length; i++){
			int distance = 0;
			for( int j = 0; j < nouns.length; j++ ){
				if( j != i )
				{
					distance += wordNet.distance(nouns[j], nouns[i]);
				}
			}
			if(distance>maxDistance)
			{ 
				result = i;
				maxDistance = distance;
			} 
		} 
		return nouns[result];
	}
	public static void main(String[] args)
	{
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) 
		{
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}
}
