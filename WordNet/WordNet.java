import java.util.ArrayList;
import java.util.HashMap;

public class WordNet {
	private HashMap<String, Bag<Integer>> nouns;
	private HashMap<Integer, ArrayList<String>> Synsets;
	private Digraph wordNet;
	private ArrayList<Integer> potentialRoots;
	private SAP sap;
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms)
	{
		if(synsets == null || hypernyms == null) throw new NullPointerException("Cannot have null arguments.");

		Synsets = new HashMap<Integer, ArrayList<String>>();
		nouns = new HashMap<String, Bag<Integer>>();
		In in = new In(synsets);      // input file

		while (!in.isEmpty()) {
			String[] synsetInfo = in.readLine().split(",");
			String[] synonyms = synsetInfo[1].split(" ");
			ArrayList<String> synonymBag = new ArrayList<String>();

			for(int i=0;i<synonyms.length;i++){
				synonymBag.add(synonyms[i]);
				if(!nouns.containsKey(synonyms[i])){
					Bag<Integer> bag = new Bag<Integer>();
					bag.add(Integer.parseInt(synsetInfo[0]));
					nouns.put(synonyms[i], bag);
				}else{
					nouns.get(synonyms[i]).add(Integer.parseInt(synsetInfo[0]));
				}
			}
			Synsets.put(Integer.parseInt(synsetInfo[0]), synonymBag);
		}
		in.close();

		in = new In(hypernyms);
		wordNet = new Digraph(Synsets.size());
		potentialRoots = new ArrayList<Integer>();

		while(!in.isEmpty()){
			String[] hypernymInfo = in.readLine().split(",");
			int fromVertex = Integer.parseInt(hypernymInfo[0]);

			for(int i=1;i<hypernymInfo.length;i++){
				if(potentialRoots.contains((Integer)fromVertex)){
					potentialRoots.remove((Integer)fromVertex);
				}
				int toVertex = Integer.parseInt(hypernymInfo[i]);
				Iterable<Integer> validity = wordNet.adj(toVertex);
				if(!validity.iterator().hasNext()){
					if(!potentialRoots.contains((Integer)toVertex))
						potentialRoots.add(toVertex);
				}
				wordNet.addEdge(fromVertex, toVertex);
			}
		}
		if(potentialRoots.size()>1) throw new IllegalArgumentException("Cannot have multiple roots in a DAG.");
		potentialRoots.trimToSize();
		if(new DirectedCycle(wordNet).hasCycle()) throw new IllegalArgumentException();
		sap = new SAP(new Digraph(wordNet));
	}

	// returns all WordNet nouns
	public Iterable<String> nouns()
	{
		return nouns.keySet();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word)
	{
		if(word==null){
			throw new NullPointerException();
		}
		return nouns.containsKey(word);   
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB)
	{
		if(nounA == null || nounB == null){
			throw new NullPointerException();
		}
		if(!nouns.containsKey(nounA) || !nouns.containsKey(nounB)){
			throw new IllegalArgumentException();
		}
		return sap.length(nouns.get(nounA), nouns.get(nounB));
	}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB)
	{	
		if(nounA == null || nounB == null){
			throw new NullPointerException();
		}
		if(!nouns.containsKey(nounA) || !nouns.containsKey(nounB)){
			throw new IllegalArgumentException();
		}
		StringBuilder sb = new StringBuilder();
		for(String synset : Synsets.get(sap.ancestor(nouns.get(nounA), nouns.get(nounB)))){
			sb.append(synset + " ");
		}
		return sb.toString();
	}

	// do unit testing of this class
	public static void main(String[] args)
	{
		/*
	   WordNet wn = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
	   wn.nouns();
		 */
	}
}