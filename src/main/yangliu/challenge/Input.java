package main.yangliu.challenge;

public class Input {
	
	public static String[] readArguments(String[] args){
		String[] result = null;
		if(args != null){
			result = new String[]{null, null};
			boolean hasN = false; // don't check -n if already found
			boolean hasD = false; // don't check -d if already found
			for(int i=0; i < args.length; i++){
				String s = args[i];
				// have parameter -n or -N
				if(!hasN && (s.equals("-n") || s.equals("-N"))){
					hasN = true;
					if(i + 1 < args.length){
						String phoneNumber = args[i+1];
						// Remove all non-numbers
						phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
						if(phoneNumber.length() > 0)
							result[0] = phoneNumber;
						i++;
					}
				// have parameter -d or -D
				}else if(!hasD && (s.equals("-d") || s.equals("-D"))){
					hasD = true;
					if(i + 1 < args.length){
						String dicPath = args[i+1];
						result[1] = dicPath;
					}
				}
			}
		}
		return result;
	}
}
