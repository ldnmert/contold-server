package com.contold.mapper;

import java.util.ArrayList;
import java.util.List;

import com.contold.dto.DisplayFollowerDTO;
import com.contold.dto.DisplayFollowingDTO;
import com.contold.entity.Follow;

public class FollowMapper {

	public static DisplayFollowerDTO fromEntityToDisplaayFollowerDTO(Follow follow) {

		DisplayFollowerDTO DisplayFollowerDTO = new DisplayFollowerDTO();

		DisplayFollowerDTO.setUsername(follow.getFollowed().getUsername());

		return DisplayFollowerDTO;
	}
	
	public static List<DisplayFollowerDTO> fromEntityToDisplaayFollowerDTO(List<Follow> followList){
		
		List<DisplayFollowerDTO> DisplayFollowerDTOList = new ArrayList<DisplayFollowerDTO>();
		
		for(Follow f : followList) {
			DisplayFollowerDTOList.add(fromEntityToDisplaayFollowerDTO(f));
		}
		
		return DisplayFollowerDTOList;
	}
	
	
	public static DisplayFollowingDTO fromEntityToDisplayFollowingDTO(Follow follow) {

		DisplayFollowingDTO DisplayFollowingDTO = new DisplayFollowingDTO();

		DisplayFollowingDTO.setUsername(follow.getFollower().getUsername());

		return DisplayFollowingDTO;
	}
	
	public static List<DisplayFollowingDTO> fromEntityToDisplayFollowingDTO(List<Follow> followList){
		
		List<DisplayFollowingDTO> DisplayFollowingDTOList = new ArrayList<DisplayFollowingDTO>();
		
		for(Follow f : followList) {
			DisplayFollowingDTOList.add(fromEntityToDisplayFollowingDTO(f));
		}
		
		return DisplayFollowingDTOList;
	}
}
