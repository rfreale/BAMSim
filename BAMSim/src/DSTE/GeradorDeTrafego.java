package DSTE;

import java.io.IOException;

import org.jrobin.core.RrdException;

import Simulador.*;

public class GeradorDeTrafego {

	private static int numeroDeLSPsPorMinuto[][];
	private static int numeroDeLSPsPorSlot[][];
	private static int CT0=10;//40
	private static int CT1=12;//35
	private static int CT2=8;//25
	private static int [][]matriz =
		{ 
			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+6, CT1+0, CT2+7},	{CT0+5, CT1+0, CT2+8},	{CT0+4, CT1+0, CT2+9},	{CT0+3, CT1+0, CT2+10},	{CT0+2, CT1+0, CT2+11},	{CT0+1, CT1+0, CT2+12},	{CT0+1, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+14},	{CT0+0, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+12},	{CT0+0, CT1+0, CT2+11},	{CT0+0, CT1+0, CT2+10},	{CT0+0, CT1+0, CT2+9},	{CT0+0, CT1+0, CT2+8},	{CT0+0, CT1+0, CT2+7},	{CT0+0, CT1+0, CT2+6},	{CT0+0, CT1+0, CT2+5},	{CT0+0, CT1+0, CT2+4},	{CT0+0, CT1+0, CT2+3},	{CT0+0, CT1+0, CT2+2},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+0},
			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},
			{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},

			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+6, CT1+0, CT2+7},	{CT0+5, CT1+0, CT2+8},	{CT0+4, CT1+0, CT2+9},	{CT0+3, CT1+0, CT2+10},	{CT0+2, CT1+0, CT2+11},	{CT0+1, CT1+0, CT2+12},	{CT0+1, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+14},	{CT0+0, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+12},	{CT0+0, CT1+0, CT2+11},	{CT0+0, CT1+0, CT2+10},	{CT0+0, CT1+0, CT2+9},	{CT0+0, CT1+0, CT2+8},	{CT0+0, CT1+0, CT2+7},	{CT0+0, CT1+0, CT2+6},	{CT0+0, CT1+0, CT2+5},	{CT0+0, CT1+0, CT2+4},	{CT0+0, CT1+0, CT2+3},	{CT0+0, CT1+0, CT2+2},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+0},
			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},
			{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},


			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+6, CT1+0, CT2+7},	{CT0+5, CT1+0, CT2+8},	{CT0+4, CT1+0, CT2+9},	{CT0+3, CT1+0, CT2+10},	{CT0+2, CT1+0, CT2+11},	{CT0+1, CT1+0, CT2+12},	{CT0+1, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+14},	{CT0+0, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+12},	{CT0+0, CT1+0, CT2+11},	{CT0+0, CT1+0, CT2+10},	{CT0+0, CT1+0, CT2+9},	{CT0+0, CT1+0, CT2+8},	{CT0+0, CT1+0, CT2+7},	{CT0+0, CT1+0, CT2+6},	{CT0+0, CT1+0, CT2+5},	{CT0+0, CT1+0, CT2+4},	{CT0+0, CT1+0, CT2+3},	{CT0+0, CT1+0, CT2+2},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+0},
			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},
			{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},


			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+6, CT1+0, CT2+7},	{CT0+5, CT1+0, CT2+8},	{CT0+4, CT1+0, CT2+9},	{CT0+3, CT1+0, CT2+10},	{CT0+2, CT1+0, CT2+11},	{CT0+1, CT1+0, CT2+12},	{CT0+1, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+14},	{CT0+0, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+12},	{CT0+0, CT1+0, CT2+11},	{CT0+0, CT1+0, CT2+10},	{CT0+0, CT1+0, CT2+9},	{CT0+0, CT1+0, CT2+8},	{CT0+0, CT1+0, CT2+7},	{CT0+0, CT1+0, CT2+6},	{CT0+0, CT1+0, CT2+5},	{CT0+0, CT1+0, CT2+4},	{CT0+0, CT1+0, CT2+3},	{CT0+0, CT1+0, CT2+2},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+0},
			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},
			{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},


			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+6, CT1+0, CT2+7},	{CT0+5, CT1+0, CT2+8},	{CT0+4, CT1+0, CT2+9},	{CT0+3, CT1+0, CT2+10},	{CT0+2, CT1+0, CT2+11},	{CT0+1, CT1+0, CT2+12},	{CT0+1, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+14},	{CT0+0, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+12},	{CT0+0, CT1+0, CT2+11},	{CT0+0, CT1+0, CT2+10},	{CT0+0, CT1+0, CT2+9},	{CT0+0, CT1+0, CT2+8},	{CT0+0, CT1+0, CT2+7},	{CT0+0, CT1+0, CT2+6},	{CT0+0, CT1+0, CT2+5},	{CT0+0, CT1+0, CT2+4},	{CT0+0, CT1+0, CT2+3},	{CT0+0, CT1+0, CT2+2},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+0},
			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},
			{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},


			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+6, CT1+0, CT2+7},	{CT0+5, CT1+0, CT2+8},	{CT0+4, CT1+0, CT2+9},	{CT0+3, CT1+0, CT2+10},	{CT0+2, CT1+0, CT2+11},	{CT0+1, CT1+0, CT2+12},	{CT0+1, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+14},	{CT0+0, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+12},	{CT0+0, CT1+0, CT2+11},	{CT0+0, CT1+0, CT2+10},	{CT0+0, CT1+0, CT2+9},	{CT0+0, CT1+0, CT2+8},	{CT0+0, CT1+0, CT2+7},	{CT0+0, CT1+0, CT2+6},	{CT0+0, CT1+0, CT2+5},	{CT0+0, CT1+0, CT2+4},	{CT0+0, CT1+0, CT2+3},	{CT0+0, CT1+0, CT2+2},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+0},
			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},
			{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},


			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+6, CT1+0, CT2+7},	{CT0+5, CT1+0, CT2+8},	{CT0+4, CT1+0, CT2+9},	{CT0+3, CT1+0, CT2+10},	{CT0+2, CT1+0, CT2+11},	{CT0+1, CT1+0, CT2+12},	{CT0+1, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+14},	{CT0+0, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+12},	{CT0+0, CT1+0, CT2+11},	{CT0+0, CT1+0, CT2+10},	{CT0+0, CT1+0, CT2+9},	{CT0+0, CT1+0, CT2+8},	{CT0+0, CT1+0, CT2+7},	{CT0+0, CT1+0, CT2+6},	{CT0+0, CT1+0, CT2+5},	{CT0+0, CT1+0, CT2+4},	{CT0+0, CT1+0, CT2+3},	{CT0+0, CT1+0, CT2+2},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+0},
			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},
			{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},


			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+6, CT1+0, CT2+7},	{CT0+5, CT1+0, CT2+8},	{CT0+4, CT1+0, CT2+9},	{CT0+3, CT1+0, CT2+10},	{CT0+2, CT1+0, CT2+11},	{CT0+1, CT1+0, CT2+12},	{CT0+1, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+14},	{CT0+0, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+12},	{CT0+0, CT1+0, CT2+11},	{CT0+0, CT1+0, CT2+10},	{CT0+0, CT1+0, CT2+9},	{CT0+0, CT1+0, CT2+8},	{CT0+0, CT1+0, CT2+7},	{CT0+0, CT1+0, CT2+6},	{CT0+0, CT1+0, CT2+5},	{CT0+0, CT1+0, CT2+4},	{CT0+0, CT1+0, CT2+3},	{CT0+0, CT1+0, CT2+2},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+0},
			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},
			{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},


			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+6, CT1+0, CT2+7},	{CT0+5, CT1+0, CT2+8},	{CT0+4, CT1+0, CT2+9},	{CT0+3, CT1+0, CT2+10},	{CT0+2, CT1+0, CT2+11},	{CT0+1, CT1+0, CT2+12},	{CT0+1, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+14},	{CT0+0, CT1+0, CT2+13},	{CT0+0, CT1+0, CT2+12},	{CT0+0, CT1+0, CT2+11},	{CT0+0, CT1+0, CT2+10},	{CT0+0, CT1+0, CT2+9},	{CT0+0, CT1+0, CT2+8},	{CT0+0, CT1+0, CT2+7},	{CT0+0, CT1+0, CT2+6},	{CT0+0, CT1+0, CT2+5},	{CT0+0, CT1+0, CT2+4},	{CT0+0, CT1+0, CT2+3},	{CT0+0, CT1+0, CT2+2},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+1},	{CT0+0, CT1+0, CT2+0},
			{CT0+0, CT1+0, CT2+0},	{CT0+1, CT1+0, CT2+0},	{CT0+2, CT1+0, CT2+0},	{CT0+3, CT1+0, CT2+0},	{CT0+4, CT1+0, CT2+0},	{CT0+5, CT1+0, CT2+0},	{CT0+6, CT1+0, CT2+0},	{CT0+7, CT1+0, CT2+0},	{CT0+8, CT1+0, CT2+0},	{CT0+9, CT1+0, CT2+0},	{CT0+10, CT1+0, CT2+0},	{CT0+11, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+14, CT1+0, CT2+0},	{CT0+13, CT1+0, CT2+0},	{CT0+12, CT1+0, CT2+1},	{CT0+11, CT1+0, CT2+2},	{CT0+10, CT1+0, CT2+3},	{CT0+9, CT1+0, CT2+4},	{CT0+8, CT1+0, CT2+5},	{CT0+7, CT1+0, CT2+6},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},
			{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+7, CT1+0, CT2+7},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+9},	{CT0+12, CT1+0, CT2+10},	{CT0+12, CT1+0, CT2+10},	{CT0+10, CT1+0, CT2+12},	{CT0+10, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+9, CT1+0, CT2+12},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+10},	{CT0+13, CT1+0, CT2+8},	{CT0+13, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+8, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},	{CT0+13, CT1+0, CT2+13},

			
			
		};
	
	// Aleatório3
		public static void trafegoForcado(RodadaDeSimulacao rodada, Topologia to, No dados) {
			
				
			if (dados == null) {
				dados = new No();
				dados.item = 0;
				Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + 0);
				rodada.schedulep(3, 0, dados);

			} else {

				int slot = (int) dados.item;
				/*if(slot%5==4)
				{
					try {
						if(rodada.estatistica.devolucoes(300)>=5||rodada.estatistica.preempcoes(300)>=5)
						{
							Lsp LSPaux= new Lsp(); 
							LSPaux.Carga=0; 
							to.link[0].bamType = BAMType.PreemptionGBAM;
							to.link[0].BCLTH= new double[]
							{	000, //BC0 
								000, //BC1
								0  //BC2 Nunca mudar
							};
							LSPaux.CT=0; 
				      		BAM.devolutionG(to.link[0],LSPaux);
							
							
							to.link[0].BCHTL= new double[]
							{	0, //BC0 Nunca mudar
								000, //BC1
								000 //BC2
							};
							
							LSPaux.CT=2; 
				      		BAM.preemptionG(to.link[0],LSPaux); 
							
						}
						else if(rodada.estatistica.picoDeUtilizacaoDoEnlace(300,to.link[0])<=800)
						{
							to.link[0].bamType = BAMType.PreemptionGBAM;
							to.link[0].BCLTH= new double[]
							{	100, //BC0 
								100, //BC1
								0  //BC2 Nunca mudar
							};
							
							to.link[0].BCHTL= new double[]
							{	0, //BC0 Nunca mudar
								100, //BC
								100 //BC2
							};
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RrdException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				
				}
				*/

				for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {

					for (int i = 0; i < matriz[slot][ct]; i++) {
						No dadosLSP = new No();

						Lsp lsp = new Lsp(rodada);
						lsp.CargaReduzida = 0;
						lsp.src = 0; // id do router fonte
						lsp.dest = 1; // id do router destino
						lsp.CT = ct;
						lsp.Carga = 10;//(int) GeradorDeNumerosAleatorios.uniform(5, 15);
						lsp.tempoDeVida = 120;//(int) GeradorDeNumerosAleatorios.expntl(250);
						dadosLSP.item = lsp;

						Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID + " - "
								+ to.getRoteador(((Lsp) dadosLSP.item).src).getDescricao() + " -->"
								+ to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao());
						rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform(0, 59), dadosLSP);

					}
				}
				Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + slot);
				dados.item = slot + 1;
				rodada.schedulep(3, 60, dados);

			}

		}
	public static void trafegoDeterministico(RodadaDeSimulacao rodada, Topologia to, No dados) {

		// Inicializar Tráfego
		if (dados == null) {
			for (int i = 0; i < ParametrosDSTE.MaxClassType; i++) {
				int auxCT = i;
				dados = new No();
				Lsp lsp = new Lsp(rodada);
				lsp.CargaReduzida = 0;
				lsp.src = 0; // id do router fonte
				lsp.dest = 1; // id do router destino
				lsp.CT = auxCT;
				lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
				dados.item = lsp;
				Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
						+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
						+ to.getRoteador(((Lsp) dados.item).dest).getDescricao());
				rodada.schedulep(3, 3600 * 0, dados);
			}
		} else {
			Debug.setMensagem(
					"Cria LSP " + ((Lsp) dados.item).ID + " - " + to.getRoteador(((Lsp) dados.item).src).getDescricao()
							+ " -->" + to.getRoteador(((Lsp) dados.item).dest).getDescricao());
			rodada.schedulep(1, 0, dados);

			// Repetição do tráfego
			Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
					+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
					+ to.getRoteador(((Lsp) dados.item).dest).getDescricao());
			int auxCT = ((Lsp) dados.item).CT;
			dados = new No();
			Lsp lsp = new Lsp(rodada);
			lsp.CargaReduzida = 0;
			lsp.src = 0; // id do router fonte
			lsp.dest = 1; // id do router destino
			lsp.CT = auxCT;
			lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
			dados.item = lsp;

			double tempoDeVida = 250;

			if (rodada.simtime() < 3600 * 1) // Uma hora de 0 a 1 hora
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 2) 
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 3) 
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 4)// 14.400 de 3 a 4 horas
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}
			} else if (rodada.simtime() < 3600 * 5) // 18000 de 4 a 5 horas
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(2), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 6) // de 5 a 6 horas
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(2), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 7)
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(2), dados);
				}
			} else if (rodada.simtime() < 3600 * 8){
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 9)
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 10) 
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 11) 
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 12)
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(100), dados);
				}
			} else if (rodada.simtime() < 3600 * 13)
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);
				}
			} else if (rodada.simtime() < 3600 * 14)
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);
				}
			} else if (rodada.simtime() < 3600 * 15) 
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);
				}
			} else if (rodada.simtime() < 3600 * 16) /// de 15 as 16
														/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 17) /// de 16 as 17
														/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}

			}

		}

	}

	public static void trafegoDeterministico2(RodadaDeSimulacao rodada, Topologia to, No dados) {

		// Inicializar Tráfego
		if (dados == null) {
			for (int i = 0; i < ParametrosDSTE.MaxClassType; i++) {
				int auxCT = i;
				dados = new No();
				Lsp lsp = new Lsp(rodada);
				lsp.CargaReduzida = 0;
				lsp.src = 0; // id do router fonte
				lsp.dest = 1; // id do router destino
				lsp.CT = auxCT;
				lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
				dados.item = lsp;
				Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
						+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
						+ to.getRoteador(((Lsp) dados.item).dest).getDescricao());
				if (((Lsp) dados.item).CT == 2) {

					rodada.schedulep(3, 3600 * 0, dados); //// CT2
					rodada.schedulep(3, 3600 * 15, dados);

				} else if (((Lsp) dados.item).CT == 1) {

					rodada.schedulep(3, 3600 * 4, dados); // CT1
					rodada.schedulep(3, 3600 * 10, dados);

				} else {
					rodada.schedulep(3, 3600 * 2, dados); //// CT0
					rodada.schedulep(3, 3600 * 9, dados);
					rodada.schedulep(3, 3600 * 16, dados);
				}
			}
		} else {
			Debug.setMensagem(
					"Cria LSP " + ((Lsp) dados.item).ID + " - " + to.getRoteador(((Lsp) dados.item).src).getDescricao()
							+ " -->" + to.getRoteador(((Lsp) dados.item).dest).getDescricao());
			rodada.schedulep(1, 0, dados);

			// Repetição do tráfego
			Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
					+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
					+ to.getRoteador(((Lsp) dados.item).dest).getDescricao());
			int auxCT = ((Lsp) dados.item).CT;
			dados = new No();
			Lsp lsp = new Lsp(rodada);
			lsp.CargaReduzida = 0;
			lsp.src = 0; // id do router fonte
			lsp.dest = 1; // id do router destino
			lsp.CT = auxCT;
			lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
			dados.item = lsp;

			double tempoDeVida = 250;

			if (rodada.simtime() < 3600 * 1) // Uma hora de 0 a 1 hora
			{
				((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
				rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);

			} else if (rodada.simtime() < 3600 * 2) // 7200 Duas horas de 1 a 2
													// horas
			{

				((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
				rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

			} else if (rodada.simtime() < 3600 * 3) // 10800 Três horas de 2 a 3
													// horas
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 4)// 14.400 de 3 a 4 horas
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}
			} else if (rodada.simtime() < 3600 * 5) // 18000 de 4 a 5 horas
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}
			} else if (rodada.simtime() < 3600 * 6) // de 5 a 6 horas
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}
			} else if (rodada.simtime() < 3600 * 7) /// de 6 a 7
													/// horas//////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 8) /// de 7 as 8
													/// horas//////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				}

			} else if (rodada.simtime() < 3600 * 9) /////// de 8 as 9
													/////// horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{

			} else if (rodada.simtime() < 3600 * 10) /////// de 9 as
														/////// 10horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}

			} else if (rodada.simtime() < 3600 * 11) /////// de 10 as 11
														/////// horas///////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				}

			} else if (rodada.simtime() < 3600 * 12) /////// de 11 as 12
														/////// horas//////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 13) //// de 12 as 13
														//// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(15), dados);
				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 14) //// de 13 as 14
														//// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}

			} else if (rodada.simtime() < 3600 * 15) //// de 14 as 15
														//// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(5), dados);
				}

			} else if (rodada.simtime() < 3600 * 16) /// de 15 as 16
														/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(6), dados);
				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(6), dados);
				}

			} else if (rodada.simtime() < 3600 * 17) /// de 16 as 17
														/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 1) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);

				} else if (((Lsp) dados.item).CT == 2) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				}

			} else if (rodada.simtime() < 3600 * 18) /// de 16 as 17
			/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
			{

				if (((Lsp) dados.item).CT == 0) {
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
				} else if (((Lsp) dados.item).CT == 1) 
				{
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				} else if (((Lsp) dados.item).CT == 2) 
				{
					((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
					rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
				}
			}else if (rodada.simtime() < 3600 * 19) /// de 16 as 17
				/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					}
				}else if (rodada.simtime() < 3600 * 20) /// de 16 as 17
					/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					}
				}else if (rodada.simtime() < 3600 * 21) /// de 16 as 17
					/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					}
				}else if (rodada.simtime() < 3600 * 22) /// de 16 as 17
					/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					}
				}else if (rodada.simtime() < 3600 * 23) /// de 16 as 17
					/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(3), dados);
					}
				}else if (rodada.simtime() < 3600 * 24) /// de 16 as 17
					/// horas/////////////////////////////////////////////////////////////////////////////////////////////////////
				{

					if (((Lsp) dados.item).CT == 0) {
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(10), dados);
					} else if (((Lsp) dados.item).CT == 1) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(20), dados);
					} else if (((Lsp) dados.item).CT == 2) 
					{
						((Lsp) dados.item).tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(tempoDeVida);
						rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.expntl(7), dados);
					}
				}
			
			
			
			
			
			
			
			
			
			

		}

	}

	// Aleatório1
	public static void trafegoAleatorio(RodadaDeSimulacao rodada, Topologia to, No dados) {

		if (dados != null) {
			Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
					+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
					+ to.getRoteador(((Lsp) dados.item).dest).getDescricao());
			rodada.schedulep(1, 0.0, dados);
		}

		dados = new No();
		Lsp lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																						// do
																						// router
																						// fonte
		do {
			lsp.dest = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																								// do
																								// router
																								// destino
		} while (lsp.src == lsp.dest);
		// lsp.src = 0;
		// lsp.dest = 1;
		lsp.CT = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.MaxClassType - 1);
		lsp.Carga = GeradorDeNumerosAleatorios.uniform(5, 30);
		dados.item = lsp;
		Debug.setMensagem(
				"Cria LSP " + ((Lsp) dados.item).ID + " - " + to.getRoteador(((Lsp) dados.item).src).getDescricao()
						+ " -->" + to.getRoteador(((Lsp) dados.item).dest).getDescricao());
		((Lsp) dados.item).tempoDeVida = GeradorDeNumerosAleatorios.expntl(300);
		if (rodada.simtime() < 2000)
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(2), dados);
		else
			rodada.schedulep(3, GeradorDeNumerosAleatorios.expntl(20), dados);

	}

	// Aleatório2
	public static void trafegoManualB(RodadaDeSimulacao rodada, Topologia to, No dados) {

		if (dados != null) {
			Debug.setMensagem("Agenda estabelecimento da LSP " + ((Lsp) dados.item).ID + " - "
					+ to.getRoteador(((Lsp) dados.item).src).getDescricao() + " -->"
					+ to.getRoteador(((Lsp) dados.item).dest).getDescricao());
			rodada.schedulep(1, 0.0, dados);
		}

		dados = new No();
		Lsp lsp = new Lsp(rodada);
		lsp.CargaReduzida = 0;
		lsp.src = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																						// do
																						// router
																						// fonte
		do {
			lsp.dest = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.ROTEADORES - 1); // id
																								// do
																								// router
																								// destino
		} while (lsp.src == lsp.dest);
		// lsp.src = 0;
		// lsp.dest = 1;
		lsp.CT = GeradorDeNumerosAleatorios.uniform(0, ParametrosDSTE.MaxClassType - 1);
		lsp.Carga = GeradorDeNumerosAleatorios.uniform(50, 150);
		dados.item = lsp;
		Debug.setMensagem(
				"Cria LSP " + ((Lsp) dados.item).ID + " - " + to.getRoteador(((Lsp) dados.item).src).getDescricao()
						+ " -->" + to.getRoteador(((Lsp) dados.item).dest).getDescricao());

		if (((Lsp) dados.item).CT == 0) {
			((Lsp) dados.item).tempoDeVida = 850;
			rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.poisson(2), dados);

		} else if (((Lsp) dados.item).CT == 1) {
			((Lsp) dados.item).tempoDeVida = 850;
			rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.poisson(15), dados);

		} else if (((Lsp) dados.item).CT == 2) {
			((Lsp) dados.item).tempoDeVida = 850;
			rodada.schedulep(3, (int) GeradorDeNumerosAleatorios.poisson(1), dados);

		}

	}

	// Aleatório3
	public static void trafegoPoisson(RodadaDeSimulacao rodada, Topologia to, No dados) {

		if (dados == null) {
			numeroDeLSPsPorMinuto = gerarCurvasPoisson((int) ParametrosDSTE.TempoSimulacao + 60);
			dados = new No();
			dados.item = 0;
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + 0);
			rodada.schedulep(3, 0, dados);

		} else {

			int slot = (int) dados.item;
			for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {

				for (int i = 0; i < numeroDeLSPsPorMinuto[ct][slot]; i++) {
					No dadosLSP = new No();

					Lsp lsp = new Lsp(rodada);
					lsp.CargaReduzida = 0;
					lsp.src = 0; // id do router fonte
					lsp.dest = 1; // id do router destino
					lsp.CT = ct;
					lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
					lsp.tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(250);
					dadosLSP.item = lsp;

					Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID + " - "
							+ to.getRoteador(((Lsp) dadosLSP.item).src).getDescricao() + " -->"
							+ to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao());
					rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform(0, 59), dadosLSP);

				}
			}
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + slot);
			dados.item = slot + 1;
			rodada.schedulep(3, 60, dados);

		}

	}

	public static void trafegoPoissonTempo(RodadaDeSimulacao rodada, Topologia to, No dados) {

		if (dados == null) {

			numeroDeLSPsPorSlot = new int[][] {
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[0] * 60, 3, 1, 3),
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[1] * 60, 3, 1, 3),
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[2] * 60, 3, 1, 3) };

			dados = new No();
			dados.item = 0;
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + 0);
			rodada.schedulep(3, 0, dados);

		} else {

			int slot = (int) dados.item;
			for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {
				// if (ct!=0){
				for (int i = 0; i < numeroDeLSPsPorSlot[ct][slot]; i++) {
					No dadosLSP = new No();

					Lsp lsp = new Lsp(rodada);
					lsp.CargaReduzida = 0;
					lsp.src = 0; // id do router fonte
					lsp.dest = 1; // id do router destino
					lsp.CT = ct;
					lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
					lsp.tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(250);
					dadosLSP.item = lsp;

					Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID + " - "
							+ to.getRoteador(((Lsp) dadosLSP.item).src).getDescricao() + " -->"
							+ to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao());
					rodada.schedulep(1, GeradorDeNumerosAleatorios.expntl(3599), dadosLSP);
					// .uniform(0, 3599)
				}

				/*
				 * }else if (slot !=0){ for (int i = 0; i <
				 * numeroDeLSPsPorSlot[ct][slot-1]; i++) { No dadosLSP = new
				 * No();
				 * 
				 * Lsp lsp = new Lsp(rodada); lsp.CargaReduzida = 0; lsp.src =
				 * 0; // id do router fonte lsp.dest = 1; // id do router
				 * destino lsp.CT = ct; lsp.Carga = (int)
				 * GeradorDeNumerosAleatorios.uniform(5, 15); lsp.tempoDeVida =
				 * (int) GeradorDeNumerosAleatorios.expntl(250); dadosLSP.item =
				 * lsp;
				 * 
				 * Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID +
				 * " - " + to.getRoteador(((Lsp)
				 * dadosLSP.item).src).getDescricao() + " -->" +
				 * to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao());
				 * rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform(0,
				 * 3599), dadosLSP);
				 * 
				 * } }
				 */

			}
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + slot);
			dados.item = slot + 1;
			rodada.schedulep(3, 3600, dados);

		}

	}

	public static void trafegoPoissonTempo2(RodadaDeSimulacao rodada, Topologia to, No dados) {

		if (dados == null) {
			// numeroDeLSPsPorSlot = gerarCurvasPoisson(
			// (int)(ParametrosDSTE.TempoSimulacao/3600)+1, 60, 3);

			numeroDeLSPsPorSlot = new int[][] {
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[0] * 20, 3, 1, 3), /// numero
																				/// de
																				/// slot
																				/// ,
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[1] * 20, 3, 1, 3),
					gerarCurvasPoissonPorSlot((int) (ParametrosDSTE.TempoSimulacao / 3600) + 1,
							(int) ParametrosDSTE.BCPadrao[2] * 20, 3, 1, 3) };

			dados = new No();
			dados.item = 0;
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + 0);
			rodada.schedulep(3, 0, dados);

		} else {

			int slot = (int) dados.item;
			for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {
				// if (ct!=0){

				int numeroDeLSPsPorMinuto[] = gerarCurvasPoissonPorSlot(60, numeroDeLSPsPorSlot[ct][slot], 30, -1, -1);

				for (int i = 0; i < 60; i++) {

					for (int z = 0; z < numeroDeLSPsPorMinuto[i]; z++) {
						No dadosLSP = new No();

						Lsp lsp = new Lsp(rodada);
						lsp.CargaReduzida = 0;
						lsp.src = 0; // id do router fonte
						lsp.dest = 1; // id do router destino
						lsp.CT = ct;
						lsp.Carga = (int) GeradorDeNumerosAleatorios.uniform(5, 15);
						lsp.tempoDeVida = (int) GeradorDeNumerosAleatorios.expntl(250);
						dadosLSP.item = lsp;

						Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID + " - "
								+ to.getRoteador(((Lsp) dadosLSP.item).src).getDescricao() + " -->"
								+ to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao());

						rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform((60 * i), 59 + (60 * i)), dadosLSP);
					} // .uniform(0, 3599)

				}

				/*
				 * }else if (slot !=0){ for (int i = 0; i <
				 * numeroDeLSPsPorSlot[ct][slot-1]; i++) { No dadosLSP = new
				 * No();
				 * 
				 * Lsp lsp = new Lsp(rodada); lsp.CargaReduzida = 0; lsp.src =
				 * 0; // id do router fonte lsp.dest = 1; // id do router
				 * destino lsp.CT = ct; lsp.Carga = (int)
				 * GeradorDeNumerosAleatorios.uniform(5, 15); lsp.tempoDeVida =
				 * (int) GeradorDeNumerosAleatorios.expntl(250); dadosLSP.item =
				 * lsp;
				 * 
				 * Debug.setMensagem("Cria LSP " + ((Lsp) dadosLSP.item).ID +
				 * " - " + to.getRoteador(((Lsp)
				 * dadosLSP.item).src).getDescricao() + " -->" +
				 * to.getRoteador(((Lsp) dadosLSP.item).dest).getDescricao());
				 * rodada.schedulep(1, GeradorDeNumerosAleatorios.uniform(0,
				 * 3599), dadosLSP);
				 * 
				 * } }
				 */

			}
			Debug.setMensagem("Agenda estabelecimento de LSP do slot:" + slot);
			dados.item = slot + 1;
			rodada.schedulep(3, 3600, dados);

		}

	}

	public static int[][] gerarCurvasPoisson(int tempoEmSegundos) {

		int slotsDeTempo = tempoEmSegundos / 60;
		int numeroDeLSPsPorMinuto[][] = new int[ParametrosDSTE.MaxClassType][slotsDeTempo];
		int pesoLSPHora = 30;
		int numeroDeLSPPorHoraCT[] = new int[] { (int) ParametrosDSTE.BCPadrao[0] * pesoLSPHora,
				(int) ParametrosDSTE.BCPadrao[1] * pesoLSPHora, (int) ParametrosDSTE.BCPadrao[2] * pesoLSPHora };
		for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) {
			// System.out.println("========" + ct + "========");
			int countSlotDeTempo = 0;
			while (countSlotDeTempo < numeroDeLSPsPorMinuto[ct].length) {

				int numeroDeLSPPorHora = GeradorDeNumerosAleatorios.uniform(
						numeroDeLSPPorHoraCT[ct] - ((int) (numeroDeLSPPorHoraCT[ct] * 0.01)),
						numeroDeLSPPorHoraCT[ct] + ((int) (numeroDeLSPPorHoraCT[ct] * 0.01)));
				int lambdaPico = GeradorDeNumerosAleatorios.uniform(150, 210);
				// System.out.println("Lambda="+lambdaPico);
				int numeroDeLSPs[] = new int[lambdaPico * 3];
				for (int i = 0; i < numeroDeLSPPorHora; i++) {
					int minuto = GeradorDeNumerosAleatorios.poisson(lambdaPico);
					// if(minuto<lambdaPico*2)
					++numeroDeLSPs[minuto];
					// else
					// System.out.println("Discartado="+minuto);

				}

				int iCurva = 0;

				for (int i = 0; i < lambdaPico * 2 && numeroDeLSPs[i] < GeradorDeNumerosAleatorios.uniform(1, 3); i++) {
					// System.out.println(i+"="+numeroDeLSPs[i]);
					// System.out.println(numeroDeLSPs[i]);
					iCurva++;
				}
				int fCurva = numeroDeLSPs.length - 1;

				for (int i = numeroDeLSPs.length - 1; i > 0
						&& numeroDeLSPs[i] < GeradorDeNumerosAleatorios.uniform(2, 3); i--) {
					// System.out.println(i+"="+numeroDeLSPs[i]);
					fCurva--;
					// System.out.println(numeroDeLSPs[i]);
				}

				for (int i = iCurva; i <= fCurva && (countSlotDeTempo < numeroDeLSPsPorMinuto[ct].length); i++) {
					// System.out.println(numeroDeLSPs[i]);
					numeroDeLSPsPorMinuto[ct][countSlotDeTempo++] = numeroDeLSPs[i];
				}

			}

		}

		return numeroDeLSPsPorMinuto;
	}

	/*
	 * public static int[][] gerarCurvasPoisson(int slotsDeLSP, int pesoLSPHora,
	 * int pico) { // escala é a unidade de tempo
	 * 
	 * //int slotsDeTempo = tempoEmSegundos / escala; int
	 * numeroDeLSPsPorMinuto[][] = new
	 * int[ParametrosDSTE.MaxClassType][slotsDeLSP];
	 * 
	 * int numeroDeLSPPorHoraCT[] = new int[]
	 * {(int)ParametrosDSTE.BCPadrao[0]*pesoLSPHora,
	 * (int)ParametrosDSTE.BCPadrao[1]*pesoLSPHora,
	 * (int)ParametrosDSTE.BCPadrao[2]*pesoLSPHora};
	 * 
	 * for (int ct = 0; ct < ParametrosDSTE.MaxClassType; ct++) { //
	 * System.out.println("========" + ct + "========"); int countSlotDeTempo =
	 * 0; while (countSlotDeTempo < numeroDeLSPsPorMinuto[ct].length) {
	 * 
	 * int numeroDeLSPPorHora = numeroDeLSPPorHoraCT[ct]
	 * ;GeradorDeNumerosAleatorios.uniform(numeroDeLSPPorHoraCT[ct]-
	 * ((int)(numeroDeLSPPorHoraCT[ct]*0.01)),numeroDeLSPPorHoraCT[ct]+
	 * ((int)(numeroDeLSPPorHoraCT[ct]*0.01))); int lambdaPico = pico;
	 * //GeradorDeNumerosAleatorios.uniform(pico - ((int)(pico*0.01)) , pico +
	 * ((int)(pico*0.01))); // System.out.println("Lambda="+lambdaPico); int
	 * numeroDeLSPs[] = new int[lambdaPico * 2]; for (int i = 0; i <
	 * numeroDeLSPPorHora; i++) { int tempo =
	 * GeradorDeNumerosAleatorios.poisson(lambdaPico); if(tempo<lambdaPico*2){
	 * ++numeroDeLSPs[tempo]; }else{ i--;
	 * System.out.println("Discartado="+tempo); } //
	 * System.out.println("Discartado="+minuto);
	 * 
	 * }
	 * 
	 * int iCurva = 0;
	 * 
	 * for (int i = 0; i < lambdaPico * 2 && numeroDeLSPs[i] <
	 * GeradorDeNumerosAleatorios.uniform(1, 3); i++) { //
	 * System.out.println(i+"="+numeroDeLSPs[i]); //
	 * System.out.println(numeroDeLSPs[i]); iCurva++; } int fCurva =
	 * numeroDeLSPs.length - 1;
	 * 
	 * for (int i = numeroDeLSPs.length - 1; i > 0 && numeroDeLSPs[i] <
	 * GeradorDeNumerosAleatorios.uniform(2, 3); i--) { //
	 * System.out.println(i+"="+numeroDeLSPs[i]); fCurva--; //
	 * System.out.println(numeroDeLSPs[i]); }
	 * 
	 * for (int i = iCurva; i <= fCurva && (countSlotDeTempo <
	 * numeroDeLSPsPorMinuto[ct].length); i++) { //
	 * System.out.println(numeroDeLSPs[i]);
	 * numeroDeLSPsPorMinuto[ct][countSlotDeTempo++] = numeroDeLSPs[i]; }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return numeroDeLSPsPorMinuto; }
	 */

	public static int[] gerarCurvasPoissonPorSlot(int slotsDeLSP, int numeroDeLSPSlot, int pico, int corteInicio,
			int corteFim) { // escala é a unidade de tempo

		// int slotsDeTempo = tempoEmSegundos / escala;
		int numeroDeLSPsPorSlot[] = new int[slotsDeLSP];

		int countSlotDeTempo = 0;
		while (countSlotDeTempo < numeroDeLSPsPorSlot.length) {

			int lambdaPico = pico;
			int numeroDeLSPs[] = new int[lambdaPico * 2];
			for (int i = 0; i < numeroDeLSPSlot; i++) {
				int tempo = GeradorDeNumerosAleatorios.poisson(lambdaPico);
				if (tempo < lambdaPico * 2) {
					++numeroDeLSPs[tempo];
				}
			}
			int iCurva = 0;

			if (corteInicio == -1) {
				iCurva = 0;
			} else {
				for (int i = 0; i < lambdaPico * 2 && numeroDeLSPs[i] < corteInicio; i++) {
					// System.out.println(i+"="+numeroDeLSPs[i]);
					// System.out.println(numeroDeLSPs[i]);
					iCurva++;
				}
			}

			int fCurva = numeroDeLSPs.length - 1;

			if (corteFim == -1) {
				fCurva = 2 * pico;
			} else {
				for (int i = numeroDeLSPs.length - 1; i > 0 && numeroDeLSPs[i] < corteFim; i--) {
					// System.out.println(i+"="+numeroDeLSPs[i]);
					fCurva--;
					// System.out.println(numeroDeLSPs[i]);
				}

			}

			for (int i = iCurva; i <= fCurva && (countSlotDeTempo < numeroDeLSPsPorSlot.length); i++) {
				// System.out.println(numeroDeLSPs[i]);
				numeroDeLSPsPorSlot[countSlotDeTempo++] = numeroDeLSPs[i];
			}

		}

		return numeroDeLSPsPorSlot;
	}

}
