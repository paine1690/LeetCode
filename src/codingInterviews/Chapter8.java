package codingInterviews;

import java.util.ArrayList;
import java.util.List;

public class Chapter8 {

	
	
	static void dfs(StringBuilder s, TreeNode root){
		if(root==null){
			s.append("$,");
			return;
		}
		s.append(root.val).append(",");
		dfs(s, root.left);
		dfs(s, root.right);
	}
	
    static String Serialize(TreeNode root) {
    	StringBuilder s=new StringBuilder();
    	dfs(s, root);
    	return s.toString();
    }
    
    static int index;
    
    static TreeNode create(String s){
    	if(index>=s.length()){
    		return null;
    	}
    	
    	TreeNode nNode;
    	if(s.charAt(index)=='$'){
    		nNode=null;
    		index+=2;
    	}else{
    		int i=0;
    		for(i=index+1; i<s.length(); i++){
    			if(s.charAt(i)==','){
    				break;
    			}
    		}    		
    		int val=Integer.valueOf(s.substring(index, i));
    		nNode=new TreeNode(val);
    		index=i+1;
    		nNode.left=create(s);
        	nNode.right=create(s);
    	}
    	return nNode;
    }
    
    static TreeNode Deserialize(String str) {
    	index=0;
    	return create(str);
    }
    
    
    public static List<List<Integer>> levelOrder2(TreeNode root) {
    	List<List<Integer>> re=new ArrayList<List<Integer>>();
    	if(root==null){
    		return re;
    	}
    	List<TreeNode> list=new ArrayList<TreeNode>();
    	list.add(root);
    	int cur=0;
    	int last=1;
    	while(cur<list.size()){
    		last=list.size();
    		List<Integer> temp=new ArrayList<Integer>();
    		while(cur<last){
    			TreeNode tempNode=list.get(cur);
    			temp.add(tempNode.val);
    			if(tempNode.left!=null){
    				list.add(tempNode.left);
    			}
    			if(tempNode.right!=null){
    				list.add(tempNode.right);
    			}
    			cur++;
    		}
    		re.add(temp);
    	}    	
    	return re;
    }
	public static void main(String[] args) {
		String s="1,2,$,5,6,$,$,7,$,$,3,$,$,";
		TreeNode root=Deserialize(s);
		System.out.println(levelOrder2(root));
		System.out.println(Serialize(root));
	}

}
