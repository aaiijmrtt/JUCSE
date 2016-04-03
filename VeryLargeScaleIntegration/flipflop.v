module dflipflop(in, clk, rst, out);
	input wire in, clk, rst;
	output wire out;
	reg _out;
	initial
		_out = 1'b1;
	always @(posedge clk) begin
		if(rst == 1'b1)
			_out = 1'b1;
		else
			_out = in;
	end
	assign out = _out;
endmodule

module tflipflop(in, clk, rst, out);
	input wire in, clk, rst;
	output wire out;
	reg _out;
	initial
		_out = 1'b1;
	always @(posedge clk)
		if(rst == 1'b1)
			_out = 1'b1;
		else
			_out = _out ^ in;
	assign out = _out;
endmodule

module fourbitshiftregister(in, clk, rst, out);
	input wire in, clk, rst;
	output wire[3: 0] out;
	dflipflop m1(in, clk, rst, out[0]);
	dflipflop m2(out[0], clk, rst, out[1]);
	dflipflop m3(out[1], clk, rst, out[2]);
	dflipflop m4(out[2], clk, rst, out[3]);
endmodule
