SeamCarving is a technique currently used within photoshop.  It allows for image resizing with minimal content loss.  Instead of cropping an image to resize it, the seams with the least energy are instead removed from the image in real time.  Horizontal or vertical seams can be removed from the image to either trim the image in either direction.  Seams are removed based on their energy levels, and energy is determined by an energy function which in our case, looks at the connected pixels to the top, right, bottom and left.  

The file written by myself is SeamCarver.java.

To be completed - SeamRemoval and Tranpose functions
