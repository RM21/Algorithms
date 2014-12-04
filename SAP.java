public class SAP {
	// constructor takes a digraph (not necessarily a DAG)
	private Digraph SAPDigraph;
	
	public SAP(Digraph G)
	{
		SAPDigraph = new Digraph(G);
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w)
	{
		int result = Integer.MAX_VALUE;
		byte[] visited = new byte[SAPDigraph.V()];
		
		BreadthFirstDirectedPaths bfsFromV = new BreadthFirstDirectedPaths(SAPDigraph, v);
		BreadthFirstDirectedPaths bfsFromW = new BreadthFirstDirectedPaths(SAPDigraph, w);
		
		if(bfsFromV.hasPathTo(w)){
			if(bfsFromV.distTo(w)<result){
				result = bfsFromV.distTo(w);
			}
		}
		if(bfsFromW.hasPathTo(v)){
			if(bfsFromW.distTo(v)<result){
				result = bfsFromW.distTo(v);
			}
		}
		
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(v);
		while(!queue.isEmpty()){
			int vertex = queue.dequeue();
			for(int vert : SAPDigraph.adj(vertex)){
				if(visited[vert]!=1){
					visited[vert]=1;
					if(bfsFromV.distTo(vert)<result){
						if(bfsFromW.hasPathTo(vert)){
							if((bfsFromW.distTo(vert)+bfsFromV.distTo(vert))<result){
								result = (bfsFromW.distTo(vert)+bfsFromV.distTo(vert));
							}
						}
						queue.enqueue(vert);
					}
				}
			}
		}
		if(result==Integer.MAX_VALUE){
			result = -1;
		}
		return result;
	}
	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	public int ancestor(int v, int w)
	{
		int result = -1;
		int tempMax = Integer.MAX_VALUE;
		byte[] visited = new byte[SAPDigraph.V()];
		
		BreadthFirstDirectedPaths bfsFromV = new BreadthFirstDirectedPaths(SAPDigraph, v);
		BreadthFirstDirectedPaths bfsFromW = new BreadthFirstDirectedPaths(SAPDigraph, w);
		
		if(bfsFromV.hasPathTo(w)){
			if(bfsFromV.distTo(w)<tempMax){
				tempMax = bfsFromV.distTo(w);
				result = w;
			}
		}
		if(bfsFromW.hasPathTo(v)){
			if(bfsFromW.distTo(v)<tempMax){
				tempMax = bfsFromW.distTo(v);
				result = v;
			}
		}
		
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(v);
		while(!queue.isEmpty()){
			int vertex = queue.dequeue();
			for(int vert : SAPDigraph.adj(vertex)){
				if(visited[vert]!=1){
					visited[vert]=1;
					if(bfsFromV.distTo(vert)<tempMax){
						if(bfsFromW.hasPathTo(vert)){
							if((bfsFromW.distTo(vert)+bfsFromV.distTo(vert))<tempMax){
								tempMax = (bfsFromW.distTo(vert)+bfsFromV.distTo(vert));
								result = vert;
							}
						}
						queue.enqueue(vert);
					}
				}
			}
		}
		return result;
	}

	// length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w)
	{
		int result = Integer.MAX_VALUE;
		byte[] visited = new byte[SAPDigraph.V()];
		
		BreadthFirstDirectedPaths bfsFromV = new BreadthFirstDirectedPaths(SAPDigraph, v);
		BreadthFirstDirectedPaths bfsFromW = new BreadthFirstDirectedPaths(SAPDigraph, w);
		for(int vert : w){
			if(bfsFromV.hasPathTo(vert)){
				if(bfsFromV.distTo(vert)<result){
					result = bfsFromV.distTo(vert);
				}
			}
		}
		for(int vert : v){
			if(bfsFromW.hasPathTo(vert)){
				if(bfsFromW.distTo(vert)<result){
					result = bfsFromW.distTo(vert);
				}
			}
		}
		
		Queue<Integer> queue = new Queue<Integer>();
		for(int vert : v){
			queue.enqueue(vert);
		}
		while(!queue.isEmpty()){
			int vertex = queue.dequeue();
			for(int vert : SAPDigraph.adj(vertex)){
				if(visited[vert]!=1){
					visited[vert]=1;
					if(bfsFromV.distTo(vert)<result){
						if(bfsFromW.hasPathTo(vert)){
							if((bfsFromW.distTo(vert)+bfsFromV.distTo(vert))<result){
								result = (bfsFromW.distTo(vert)+bfsFromV.distTo(vert));
							}
						}
						queue.enqueue(vert);
					}
				}
			}
		}
		if(result==Integer.MAX_VALUE){
			result = -1;
		}
		return result;
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
	{
		int tempMax = Integer.MAX_VALUE;
		int result = -1;
		byte[] visited = new byte[SAPDigraph.V()];
		
		BreadthFirstDirectedPaths bfsFromV = new BreadthFirstDirectedPaths(SAPDigraph, v);
		BreadthFirstDirectedPaths bfsFromW = new BreadthFirstDirectedPaths(SAPDigraph, w);
		for(int vert : w){
			if(bfsFromV.hasPathTo(vert)){
				if(bfsFromV.distTo(vert)<tempMax){
					tempMax = bfsFromV.distTo(vert);
					result = vert;
				}
			}
		}
		for(int vert : v){
			if(bfsFromW.hasPathTo(vert)){
				if(bfsFromW.distTo(vert)<tempMax){
					tempMax = bfsFromW.distTo(vert);
					result = vert;
				}
			}
		}
		
		Queue<Integer> queue = new Queue<Integer>();
		for(int vert : v){
			queue.enqueue(vert);
		}
		while(!queue.isEmpty()){
			int vertex = queue.dequeue();
			for(int vert : SAPDigraph.adj(vertex)){
				if(visited[vert]!=1){
					visited[vert]=1;
					if(bfsFromV.distTo(vert)<tempMax){
						if(bfsFromW.hasPathTo(vert)){
							if((bfsFromW.distTo(vert)+bfsFromV.distTo(vert))<tempMax){
								tempMax = (bfsFromW.distTo(vert)+bfsFromV.distTo(vert));
								result = vert;
							}
						}
						queue.enqueue(vert);
					}
				}
			}
		}
		return result;
	}

	// do unit testing of this class
	public static void main(String[] args)
	{
		In in = new In("wordnet/digraph3.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);

		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length   = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}
}
 