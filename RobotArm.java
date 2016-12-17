import java.util.*;

/**
 * Your task is to implement the RobotArm API which consists of the methods given below.
 *
 * You are free to choose how you implement it, from the way you store the content to the way it will be retrieved afterwards.
 * Feel free to add any properties or auxiliary methods as you see fit. The only requirement is that you do not
 * modify the given API methods and return the correct items.
 *
 * Handle anything that fails with the same type of exception 'SomethingWentWrongException' and add a reasonable
 * description of what went wrong.
 */

public class RobotArm {

    /**
     * Constructor for the RobotArm, it takes the size of the location the robot will interact with. Each location can
     * be thought of as a matrix where each cell can contain a content, each location is a rectangle in size and will always contain the same number
     * of columns per row.
     *
     * The top left cell should be at index (0,0)
     *
     * @param numberOfRows number of rows present in the location
     * @param numberOfColumns number of columns of the location
     */
	public int noOfRows;
	public int noOfColumns;
	public Content arrangement[][];
	public List<List<String>> arrang;
    public RobotArm(Integer numberOfRows, Integer numberOfColumns) {
        if(numberOfRows<0 || numberOfColumns<0){
        	System.out.print("Please provide valid space");
        	
        }
        arrangement=new Content[numberOfRows-1][numberOfColumns-1];
    	//arrang.get(4,5);
     /*   for(int row=0;row<numberOfRows;row++){
        	for(int col=0;col<numberOfColumns;col++){
        		arrangement[row]	
        	}
        }*/
    	System.out.println("Allocated memory");
    	//throw new UnsupportedOperationException();
        
    }


    /**
     * Stores a content at the specified location (row and column)
     * @param content Content which should be stored in the location
     * @param row the row the content should be stored at
     * @param column the column the content should be stored at
     * @throws SomethingWentWrongException
     */
    public void storeItemAtLocation(Content content, Integer row, Integer column) throws SomethingWentWrongException {
        
        arrangement[row][column]=content;
      //  throw new UnsupportedOperationException();
    }


    /**
     * Retrieves the content previously stored at the given row and column. The content should not be present in the
     * location after it has been retrieved
     *
     * @param row the row the content is currently located
     * @param column the column the content is currently located
     * @return
     * @throws SomethingWentWrongException if anything goes wrong during the retrieval throw this exception with a
     * clear reason why this exception was thrown
     */
    public Content retrieveItemAtLocation(Integer row, Integer column) throws SomethingWentWrongException {
        return arrangement[row][column];
    	//throw new UnsupportedOperationException();
    }

    /**
     * Should remove any content currently stored in this location
     */
    public void removeAllContentFromLocation(Integer row, Integer column){
    	arrangement[row][column]=null;
       // throw new UnsupportedOperationException();
    }

    /**
     * Should retrieve the content with the given barcode. You are allowed to assume that barcodes will be unique. The content should not be present in the
     * location after it has been retrieved
     *
     * @param barcode the barcode for the sample
     * @return
     * @throws SomethingWentWrongException if anything goes wrong during the retrieval throw this exception with a
     * clear reason why this exception was thrown
     */
    public Content retrieveItemWithBarcode(String barcode) throws SomethingWentWrongException {
    	Content returncon=null;
    	for(int row = 0; row < arrangement.length; row++){
    		  for(int element = 0; element < arrangement[row].length; element++){
    		 
    		 if(arrangement[row][element]!=null){
    			 if(arrangement[row][element].getBarcode()==barcode){
    				 returncon= arrangement[row][element];
    			 }
    		 }
    		  }
    		}
    	//throw new UnsupportedOperationException();
    	return returncon;
    }

    /**
     * This method should fill up the location with the given contents depending on the 'FillingStrategy'.
     *
     * If the filling strategy is ROW_WISE you should start with the first available space in the top left location
     * and fill up every column until the current row is filled. Then move to the next row.
     *
     * If the filling strategy is COLUMN_WISE you should start by filling it column wise, meaning take the first
     * column and place an item in each row until the column is filled.
     *
     *
     * @param contents list of contents to be inserted into the location
     * @param strategy the strategy of how the location should be filled up
     * @throws SomethingWentWrongException
     */

    public void fillLocationWithItems(List<Content> contents, FillingStrategy strategy) throws SomethingWentWrongException {
       // throw new UnsupportedOperationException();
        
        int numberOfRows=arrangement.length;
        int numberOfColumns =arrangement[0].length;
        int contentCount=0;
        if(strategy==FillingStrategy.COLUMN_WISE){
        	int temp=numberOfRows;
        	numberOfRows=numberOfColumns;
        	numberOfColumns=temp;
        }
        
        for(int row=0;row<numberOfRows;row++){
        	for(int col=0;col<numberOfColumns;col++){
        		if(arrangement[row][col]!=null){
        			arrangement[row][col]=contents.get(contentCount);
        					contentCount=contentCount+1;
        		}
        		if(contentCount>contents.size()-1){
        			break;
        		}
        	}
        	}
        
    }

    /**
     * This method should reorder all the contents currently stored in the location. The filling is either column wise or
     * row wise, depending what type of 'FillingStrategy' was passed as a parameter.
     *
     * The order should be the volume of the content in a decreasing order (highest volume first), if two contents have
     * the same volume order them alphabetically after their barcode.
     *
     * @param strategy The strategy how the items should be recorded (row or column wise)
     */
    public void reorder(FillingStrategy strategy) {
    //    throw new UnsupportedOperationException();
    	
    	List<Content> listcon = new ArrayList<Content>();
    	for(int row = 0; row < arrangement.length; row++){
  		  for(int element = 0; element < arrangement[row].length; element++){
  		  if(arrangement[row][element]!=null){
  			listcon.add(arrangement[row][element]);
  		  }
  		  }
  		  }
        Collections.sort(listcon, new Comparator<Content>() {
        	  public int compare(Content c1, Content c2) {
        	    if (c1.getVolume() > c2.getVolume()) return 1;
        	    if (c1.getVolume() < c2.getVolume()) return -1;
        	    if(c1.getVolume()==c2.getVolume()){
        	    	if((c1.getBarcode().compareTo(c2.getBarcode())>0)) return 1;
        	    	else return 1;	 
        	    }
        	    return 0;
        	  }

        });
    
        fillLocationWithItems(listcon,strategy);
    
    }

    

    /**
     * This method will be used when the lab receives an order. A customer might request to the lab to deliver a
     * certain volume of a specified 'ContentType'. The robot should be able to retrieve the required volume from
     * the location and this method should find the minimal cost to the lab which will fulfil the order.
     *
     * Even 'Content' of the same 'ContentType' might have different prices inside the location. To collect the
     * required volume, deduct it from each 'Content' you used to fulfil the order (again try to minimize the total
     * cost).
     *
     * If the given volume can't be retrieved due to the lack of content of the given type,
     * nothing should be deducted from any item.
     *
     * Therefore this method should only modify any content in the location if enough volume is present of the specified
     * type.
     *
     * Partial removal of volume from a content is allowed to fulfil the order.
     *
     * The order returned should account for any content used (through its barcode) even if the content was only
     * partially used in that order.
     *
     * Even if all the volume of a 'Content' is used for an order you do not need to remove it from the location.
     *
     * @param volume
     * @param type
     * @return
     * @throws SomethingWentWrongException
     */
    public Order fulfilOrderWithMinimalCostForVolumeAndType(Integer volume, ContentType type) throws SomethingWentWrongException {
       // Order currentorder=new Order();
    	///Retruns iff all the requried voulme is present
    	List<String> contentBarcodesUsed = new ArrayList<String>();
    	Double price=0.0;
    	int tempvol=volume;
    	for(int items=0;items<volume;items++){
    		for(int row = 0; row < arrangement.length; row++){
      		  for(int element = 0; element < arrangement[row].length; element++){
      		    if(arrangement[row][element]!=null && arrangement[row][element].getType()==type ){
      		    	if(arrangement[row][element].getVolume()>=volume){
      		    	
      		    	
      		    	
      		    	contentBarcodesUsed.add(arrangement[row][element].getBarcode());
      		    	price=price+(arrangement[row][element].getPrice()/arrangement[row][element].getVolume())*volume;
      		    	arrangement[row][element].setVolume(arrangement[row][element].getVolume()-volume);
      		    	volume=0;
      		    	}else if(arrangement[row][element].getVolume()<=volume){
      		    		contentBarcodesUsed.add(arrangement[row][element].getBarcode());
          		    	price=price+(arrangement[row][element].getPrice()/arrangement[row][element].getVolume())*volume;
          		    	arrangement[row][element].setVolume(0);
          		    	volume=0;
          		    	break;
      		    	}else if(volume==0){
      		    		break;
      		    	}
      		    }
      			}
      		  }}
    		
    	if(volume==0){
    	return new Order(contentBarcodesUsed,price,volume,type);}else{
    		return null;
    	}
    	
    	//throw new UnsupportedOperationException();
    }


    /**
     * This method is a little bonus, you should print out (in the console) the current content stored in the location.
     * How you decide to print it out is up to you.
     *
     * @return
     */
    public String showLocationContent(Integer row, Integer column){
    	System.out.println("Item in the location "+ arrangement[row][column]);
       // throw new UnsupportedOperationException();
    	return arrangement[row][column].toString();
    }

}
