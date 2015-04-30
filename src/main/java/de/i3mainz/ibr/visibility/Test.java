package de.i3mainz.ibr.visibility;

import de.i3mainz.ibr.geometry.Point;
import de.i3mainz.ibr.geometry.Transformation;

public class Test {

	public static void main(String[] args) {
		try {
			int counter = 0;
			String pcfile = "C:\\tmp\\pc\\Gamma_01.PTG";
			String pcfile2 = "C:\\tmp\\pc\\Gamma_02.PTG";
			String pcfile3 = "C:\\tmp\\pc\\Gamma_03.PTG";
//			DiscreteSpaceFileWriter dsfw = new DiscreteSpaceFileWriter("test_ein_Standpunkt.dsf");

//			DiscreteSpaceFileWriter dsfw = new DiscreteSpaceFileWriter("Gamma.dsf", 0.05, -4, -6, -2, 400, 280, 80);
//			DiscreteSpaceFileWriter dsfw = new DiscreteSpaceFileWriter("Gamma.dsf");
		 	long time = System.currentTimeMillis();
//			dsfw.add(new Pointcloud(pcfile, new Transformation()), new Transformation());
//			dsfw.add(new Pointcloud(pcfile2, new Transformation()), new Transformation(-0.6385300828931927, 0.769577692822536, 0.005432122085786917, 5.205, -0.7695963882942519, -0.638521630684798, -0.003395036387063188, -1.806, 8.557831824909214E-4, -0.006348374403651914, 0.9999794826783074, -0.012, 0.0, 0.0, 0.0, 1.0));
//			dsfw.add(new Pointcloud(pcfile3, new Transformation()), new Transformation(0.3248730078480536, -0.9457512757874899, -0.0034717600858715264, 9.549, 0.9457444434466603, 0.32488568107524307, -0.004091689387318806, 2.993, 0.004997645598211109, -0.001954118371754676, 0.9999856023762862, 0.016, 0.0, 0.0, 0.0, 1.0));

//			DiscreteSpaceFileWriter dsfw = new DiscreteSpaceFileWriter("test_ein_Standpunkt.dsf", 0.01, -21, -20, -1, 5200, 3504, 2600);
			DiscreteSpaceFileWriter dsfw = new DiscreteSpaceFileWriter("Oberwesel.dsf");
			
//			DiscreteSpaceFileWriter dsfw = new DiscreteSpaceFileWriter("Oberwesel.dsf", 0.03, -21, -20, -1, 1736, 1168, 864);
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\011.PTG", new Transformation()), new Transformation(0.732305235699489, -0.6809765312811286, -7.490953142553827E-5, 28.617, 0.6809764397534115, 0.7323051867145407, -4.494571725799227E-4, 5.778, 3.6092642474017686E-4, 2.781282146892069E-4, 0.9999998961884007, 6.142, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\012.PTG", new Transformation()), new Transformation(0.7383611830533671, 0.6744054888270351, 0.0, -18.067, -0.6744054888270351, 0.7383611830533671, 0.0, 0.278, 0.0, 0.0, 1.0, 1.9, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\013.PTG", new Transformation()), new Transformation(-0.6184769719539196, 0.7858029238700442, 0.0, -11.907, -0.7858029238700442, -0.6184769719539196, 0.0, -0.394, 0.0, 0.0, 1.0, 1.844, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\014.PTG", new Transformation()), new Transformation(-0.7419641397107138, -0.6704395687780819, 0.0, -5.103, 0.6704395687780819, -0.7419641397107138, 0.0, -0.004, 0.0, 0.0, 1.0, 1.683, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\015.PTG", new Transformation()), new Transformation(0.8002606519973255, 0.5996523066451219, 0.0, 2.474, -0.5996523066451219, 0.8002606519973255, 0.0, 0.143, 0.0, 0.0, 1.0, 1.766, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\016.PTG", new Transformation()), new Transformation(0.8027446200359558, 0.5963229483048196, 1.2778253550776596E-4, 9.628, -0.5963229585099473, 0.8027446200827816, 6.389126736266773E-5, 0.307, -6.447691399475862E-5, -1.2748803076255378E-4, 0.9999999897947647, 1.81, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\017.PTG", new Transformation()), new Transformation(0.12128033323929746, -0.9926182956047934, 0.0, 14.827, 0.9926182956047934, 0.12128033323929746, 0.0, 0.494, 0.0, 0.0, 1.0, 2.263, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\018.PTG", new Transformation()), new Transformation(-0.9089452686778486, 0.4169154573149734, 0.0, 20.033, -0.4169154573149734, -0.9089452686778486, 0.0, 0.65, 0.0, 0.0, 0.9999999999999999, 2.279, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\019.PTG", new Transformation()), new Transformation(0.9138575395539205, -0.40603497066195493, 0.0, 26.308, 0.40603497066195493, 0.9138575395539205, 0.0, 0.867, 0.0, 0.0, 1.0, 2.768, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\020.PTG", new Transformation()), new Transformation(-0.9827583156848801, -0.18489481591493426, 0.0, 2.578, 0.18489481591493426, -0.9827583156848801, 0.0, -5.032, 0.0, 0.0, 0.9999999999999999, 1.781, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\001.PTG", new Transformation()), new Transformation(0.5004835908581313, 0.8657459970820653, 2.0932780763846637E-4, -15.871, -0.8657459404770738, 0.5004836340547792, -3.1399170171207404E-4, 10.145, -3.7660220074983345E-4, -2.4077005319453977E-5, 0.9999999287955376, 1.877, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\002.PTG", new Transformation()), new Transformation(-0.9046857759471982, 0.42607930461492877, 2.701438036671898E-4, -11.685, -0.426079320162068, -0.9046858089581321, 0.0, 8.673, 2.443952655556784E-4, -1.151026882125114E-4, 0.999999963511162, 1.826, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\003.PTG", new Transformation()), new Transformation(0.17534954153825422, 0.984506230978266, 1.3945355211159987E-4, -5.634, -0.984506227568192, 0.17534956068423574, -1.394535507556028E-4, 8.611, -1.6174600874956004E-4, -1.1283977431949885E-4, 0.9999999805527068, 1.757, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\004.PTG", new Transformation()), new Transformation(0.7852520952081138, 0.6191761841118143, 0.0, 0.089, -0.6191761620288139, 0.7852520672019941, -2.67077260282065E-4, 11.558, -1.6536787888448684E-4, 2.097229782189343E-4, 0.9999999643348679, 1.701, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\005.PTG", new Transformation()), new Transformation(-0.295441274474479, -0.9553609021395504, 0.0, 1.312, 0.9553608352725085, -0.2954412537961322, -3.741427323625963E-4, 6.187, 3.5744133831888637E-4, -1.1053720568456934E-4, 0.9999999300086054, 1.706, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\006.PTG", new Transformation()), new Transformation(0.3006225323422186, -0.9537430664647512, 5.06180368047925E-4, 8.807, 0.9537431617553825, 0.30062262953940905, 1.2654508086650448E-4, 9.4, -2.72860766735443E-4, 4.4472376197499173E-4, 0.9999998638838794, 1.674, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\007.PTG", new Transformation()), new Transformation(0.3752375968584602, -0.9269285944880965, 3.5584295637463724E-4, 15.327, 0.9269286420022637, 0.37523747948685215, -3.5584293384547E-4, 10.295, 1.9631537648473344E-4, 4.6336667567365146E-4, 0.9999998733757904, 2.159, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\008.PTG", new Transformation()), new Transformation(0.8811384459729036, -0.4728583684387744, 4.925319147136628E-5, 19.835, 0.4728582720270057, 0.8811381893627348, -7.387978042643601E-4, 9.771, 3.0594785637717847E-4, 6.742729281486571E-4, 0.9999997258759262, 2.298, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\009.PTG", new Transformation()), new Transformation(0.6444973615051925, -0.7646065285075395, -8.704306034554766E-5, 9.406, 0.7646064906862996, 0.6444973593521811, -2.6112917740949603E-4, 2.439, 2.5576009637374216E-4, 1.0174337694303654E-4, 0.9999999621175285, 7.1, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\010.PTG", new Transformation()), new Transformation(0.8191520198352497, 0.5735764192283515, 2.443460928477583E-4, 16.243, -0.5735764634001431, 0.8191520048497203, 1.832595649628799E-4, 5.545, -9.504322677268295E-5, -2.902686105747325E-4, 0.9999999533554583, 6.094, 0.0, 0.0, 0.0, 1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\021.PTG", new Transformation()), new Transformation(-0.7345475468265737,0.6785571781748966,2.3957959880009595E-4,9.225,-0.6785571976489402,-0.7345475679074958,0.0,-2.939,1.7598261161886407E-4,-1.625684611756505E-4,0.9999999713008075,7.101,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\022.PTG", new Transformation()), new Transformation(-0.8753716968738152,-0.4834504346616537,2.63700048298388E-4,16.343,0.483450451470674,-0.8753717273094908,0.0,-4.609,2.3083556677055603E-4,1.274859074026942E-4,0.9999999652311417,6.122,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\023.PTG", new Transformation()), new Transformation(-0.8385088230133244,-0.5448877778869837,-5.130674363819382E-4,27.623,0.5448873571485757,-0.8385089002115149,7.696010110727729E-4,-4.056,-8.495577965979796E-4,3.65753278535346E-4,0.9999995722379532,6.134,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\024.PTG", new Transformation()), new Transformation(-0.15703778028889492,-0.9875925959432547,0.0,-16.709,0.9875925959432547,-0.15703778028889492,0.0,-10.455,0.0,0.0,1.0,1.85,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\025.PTG", new Transformation()), new Transformation(-0.9844357714121496,-0.17574473523882864,0.0,-11.115,0.17574473523882864,-0.9844357714121496,0.0,-9.153,0.0,0.0,1.0,1.832,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\026.PTG", new Transformation()), new Transformation(0.5903943856669043,-0.8071149046901555,0.0,-4.667,0.8071149046901555,0.5903943856669043,0.0,-9.002,0.0,0.0,1.0,1.713,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\027.PTG", new Transformation()), new Transformation(0.7286220536358111,0.6849159824062607,0.0,2.464,-0.6849159824062607,0.7286220536358111,0.0,-9.625,0.0,0.0,1.0,1.715,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\028.PTG", new Transformation()), new Transformation(-0.5409304339267159,-0.8410673371685854,0.0,8.809,0.8410673371685854,-0.5409304339267159,0.0,-9.409,0.0,0.0,0.9999999999999999,1.705,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\029.PTG", new Transformation()), new Transformation(-0.6840380230423667,0.7294463537727024,0.0,15.435,-0.7294463537727024,-0.6840380230423667,0.0,-7.659,0.0,0.0,1.0,2.208,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\030.PTG", new Transformation()), new Transformation(-0.9977173982421631,0.06752772204723748,0.0,15.464,-0.06752772204723748,-0.9977173982421631,0.0,-10.499,0.0,0.0,0.9999999999999999,2.18,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\031.PTG", new Transformation()), new Transformation(0.9220794373320427,0.38700066052840737,0.0,19.98,-0.38700066052840737,0.9220794373320427,0.0,-9.196,0.0,0.0,1.0,2.364,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\032.PTG", new Transformation()), new Transformation(-0.7972152764623872,-0.6036950726992409,-2.493464610023729E-4,-1.521,0.6036951155299227,-0.7972151270458393,-4.986928909991829E-4,-3.711,1.0227567049990924E-4,-5.480948315495455E-4,0.9999998445658593,4.338,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\033.PTG", new Transformation()), new Transformation(-0.9728686648457623,0.23135734843482725,-5.816239591175153E-4,-25.104,-0.23135738756736288,-0.9728688293999377,0.0,0.553,-5.658438202576143E-4,1.3456299972801502E-4,0.9999998308567708,2.156,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\034.PTG", new Transformation()), new Transformation(-0.02701440964127659,0.9996350314698045,1.5978140168170445E-4,3.193,-0.999635032159485,-0.027014384120497625,-1.5978139964208714E-4,-20.238,-1.554066882991661E-4,-1.640394867915661E-4,0.9999999744699036,1.596,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\035.PTG", new Transformation()), new Transformation(0.750872299530459,-0.6604473178749118,3.6070718868775177E-4,39.741,0.6604471398541688,0.7508723729393211,5.049900207981499E-4,1.158,-6.043643674959776E-4,-1.4095498706297733E-4,0.999999807437683,-1.335,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\036.PTG", new Transformation()), new Transformation(-0.8555720251597612,-0.5176835325697624,-5.194902471732856E-4,7.548,0.5176836024234343,-0.8555721406064457,0.0,18.212,-4.444613827982196E-4,-2.689315825805068E-4,0.9999998650649323,0.42,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\037.PTG", new Transformation()), new Transformation(0.8516354670819696,-0.5241345515841922,-5.516985762755389E-5,-0.846,0.5241345374265289,0.851635461499379,-1.655095719590949E-4,16.359,1.3373389244316868E-4,1.1203739381441525E-4,0.999999984781434,1.439,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\038.PTG", new Transformation()), new Transformation(-0.9085300557501689,-0.4178195038513582,0.0,-6.543,0.4178194885026038,-0.9085300223749805,-2.710548655033749E-4,16.382,1.1325200942111675E-4,-2.462614920671358E-4,0.9999999632646291,0.591,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\039.PTG", new Transformation()), new Transformation(-0.9638352343999231,-0.2664982383280066,5.743668812999566E-4,-6.171,0.26649843028026987,-0.9638353096789118,2.87183405122082E-4,39.387,4.7706120936498155E-4,4.298653568630225E-4,0.9999997938141674,0.829,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\040.PTG", new Transformation()), new Transformation(-0.4201648860056905,-0.9074476887729179,4.008846651529803E-4,-12.068,0.9074477772229289,-0.420164838409929,2.0044232049669334E-4,39.041,-1.34532799119844E-5,4.480007230580393E-4,0.9999998995571757,0.828,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: "+ (counter++) + "hinzugefÃ¼gt!");
//			dsfw.add(new Pointcloud("C:\\tmp\\pc\\041.PTG", new Transformation()), new Transformation(0.8002501859683213,0.5996662737370404,0.0,59.509,-0.5996662737370404,0.8002501859683213,0.0,3.304,0.0,0.0,1.0,-3.182,0.0,0.0,0.0,1.0));
//			System.out.print("Standpunkt: alle hinzugefÃ¼gt!");
//						dsfw.export("ober_02.png", 0.2);
//						dsfw.export("ober_3.png",3.0);
//						dsfw.export("ober_2.png",2);
//						dsfw.visibillity(new Point(2, 2, 2), 2);
			dsfw.close();
			System.out.println("Laufzeit [ms]: " + (System.currentTimeMillis() - time));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
