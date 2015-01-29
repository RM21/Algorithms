Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? 
Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? 
Scientists have defined an abstract process known as percolation to model such situations.

This is a percolation application which determines whether or not a grid percolates.  It's currently built using an N*N boolean grid.
This grid is then mapped within a WeightedQuickUnion to determine whether or not there is a path from the bottom of the map, to the top.

The steps for optimization would be to prevent overwash(unncessary path highlight upon determining the grid percolates).
The other step is to convert the grid from a boolean matrix, to an integer matrix to enable a matrix of enumerations which allows
for cleaner connectedness tests.
