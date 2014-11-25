package io.lee.model;

import lombok.Getter;

public enum Channel {

	WORLD(1),

	PRIVATE(2),

	ALLY(3),

	FRIEND(4),

	TEAM(5),

	;

	@Getter
	private int id;

	private Channel(int id) {
		this.id = id;
	}
}
