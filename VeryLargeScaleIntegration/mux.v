module fourbitmuxindex(in, s, out);
	input wire[3: 0] in;
	input wire[1: 0] s;
	output wire out;
	assign out = in[s];
endmodule

module fourbitmuxconditional(in, s, out);
	input wire[3: 0] in;
	input wire[1: 0] s;
	output wire out;
	assign out = s == 2'b00 ? in[0] : s == 2'b01 ? in[1] : s == 2'b10 ? in[2] : s == 2'b11 ? in[3] : 1'b0;
endmodule

module fourbitmuxcase(in, s, out);
	input wire[3: 0] in;
	input wire[1: 0] s;
	output wire out;
	reg _out;
	always @(in or s)
		case (s)
			2'b00: _out = in[0];
			2'b01: _out = in[1];
			2'b10: _out = in[2];
			2'b11: _out = in[3];
			default: _out = 1'b0;
		endcase
	assign out = _out;
endmodule

module fourbitmux(in, s, out);
	input wire[3: 0] in;
	input wire[1: 0] s;
	output wire out;
	reg _out;
	always @(in or s)
		if(s == 2'b00)
			_out = in[0];
		else if(s == 2'b01)
			_out = in[1];
		else if(s == 2'b10)
			_out = in[2];
		else if(s == 2'b11)
			_out = in[3];
		else
			_out = 1'b0;
	assign out = _out;
endmodule

module twobitmux(in, s, out);
	input wire[1: 0] in;
	input wire s;
	output wire out;
	assign out = s == 1'b0 ? in[0] : s == 1'b1 ? in[1] : 1'b0;
endmodule

module eightbitmux(in, s, out);
	input wire[7: 0] in;
	input wire[2: 0] s;
	output wire out;
	wire[5: 0] _out;
	twobitmux m1({in[0], in[1]}, s[0], _out[0]);
	twobitmux m2({in[2], in[3]}, s[0], _out[1]);
	twobitmux m3({in[4], in[5]}, s[0], _out[2]);
	twobitmux m4({in[6], in[7]}, s[0], _out[3]);
	twobitmux m5({_out[0], _out[1]}, s[1], _out[4]);
	twobitmux m6({_out[2], _out[3]}, s[1], _out[5]);
	twobitmux m7({_out[4], _out[5]}, s[2], out);
endmodule
