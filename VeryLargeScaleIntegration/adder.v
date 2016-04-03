module halfadder(in1, in2, sum, cout);
	input wire in1, in2;
	output wire sum, cout;
	assign sum = in1 ^ in2;
	assign cout = in1 & in2;
endmodule

module fulladder(in1, in2, cin, sum, cout);
	input wire in1, in2, cin;
	output wire sum, cout;
	assign sum = in1 ^ in2 ^ cin;
	assign cout = (in1 & in2) | (in2 & cin) | (cin & in1);
endmodule

module fourbitadder(in1, in2, cin, sum, cout);
	input wire[3: 0] in1;
	input wire[3: 0] in2;
	input wire cin;
	output wire[3: 0] sum;
	output wire cout;
	wire[2: 0] _cout;
	fulladder f1(in1[0], in2[0], cin, sum[0], _cout[0]);
	fulladder f2(in1[1], in2[1], _cout[0], sum[1], _cout[1]);
	fulladder f3(in1[2], in2[2], _cout[1], sum[2], _cout[2]);
	fulladder f4(in1[3], in2[3], _cout[2], sum[3], cout);
endmodule

module addersubtractor(in1, in2, s, out, cout);
	input wire[3: 0] in1;
	input wire[3: 0] in2;
	input wire s;
	output wire[3: 0] out;
	output wire cout;
	fourbitadder a(in1, {in2[3] ^ s, in2[2] ^ s, in2[1] ^ s, in2[0] ^ s}, s, out, cout);
endmodule

module fulladdermux(in1, in2, cin, sum, cout);
	input wire in1, in2, cin;
	output wire sum, cout;
	fourbitmux m1({cin, !cin, !cin, cin}, {in1, in2}, sum);
	fourbitmux m2({1'b1, cin, cin, 1'b0}, {in1, in2}, cout);
endmodule
