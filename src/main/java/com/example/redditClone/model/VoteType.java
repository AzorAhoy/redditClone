package com.example.redditClone.model;

import java.util.Arrays;

import com.example.redditClone.exceptions.SpringRedditException;

public enum VoteType {
	UPVOTE(1), DOWNVOTE(-1);

	VoteType(int direction) {
		// TODO Auto-generated constructor stub
	}

	private int direction;

	private static VoteType lookup(Integer direction) {
		return Arrays.stream(VoteType.values()).filter(value -> value.getDirection().equals(direction)).findAny()
				.orElseThrow(() -> new SpringRedditException("VoteNotFound"));
	}

	public Integer getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}
}
