package com.onestian.craftbackup;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class craftbackup extends JavaPlugin {
	public static craftbackup plugin;
	
	public void onEnable() {
		craftbackup.plugin = this;
		
		//Metrics
		@SuppressWarnings("unused")
		Metrics metrics = new Metrics(this);
	}
}
