package com.smud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smud.model.Room;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private Room mockRoom;
	
	/* (non-Javadoc)
	 * @see com.smud.service.RoomService#getRoom(int)
	 */
	@Override
	public Room getRoom(int code) {
		return mockRoom;
	}
	
}
